package com.pl.data;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.List;

public class RawSqlPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
                                              IntrospectedTable introspectedTable) {

        topLevelClass.getInnerClasses().stream()
                .filter(this::isGeneratedCriteria)
                .findFirst()
                .ifPresent(c -> addRowSql(topLevelClass,introspectedTable, c));

        return true;
    }

    private boolean isGeneratedCriteria(InnerClass innerClass) {
        return "Criteria".equals(innerClass.getType().getShortName()); //$NON-NLS-1$
    }

    private void addRowSql(TopLevelClass topLevelClass,IntrospectedTable introspectedTable, InnerClass criteria) {
        add(criteria);
        add2(topLevelClass);
    }
    private void add2(TopLevelClass topLevelClass) {
        Method setOffset = new Method();
        setOffset.setVisibility(JavaVisibility.PUBLIC);
        setOffset.setName("handleQueryArgs");
        setOffset.setReturnType(topLevelClass.getType());
        setOffset.addParameter(new Parameter(new FullyQualifiedJavaType("com.cooee.reader.common.api.IQueryArgs"), "query"));
        setOffset.addBodyLine("if(query==null) return this;\n" +
                "        if(query.hasSql()){\n" +
                "            createCriteria().addCriterion(query.toSql());\n" +
                "        }\n" +
                "        setOrderByClause(\"id desc\");\n" +
                "        setLimit(query.getSize());\n" +
                "        if(query.makeStart()!=null) setOffset(query.makeStart());\n" +
                "        return this;");
        topLevelClass.addMethod(setOffset);
    }
    private void add(InnerClass criteria) {
        Method setOffset = new Method();
        setOffset.setVisibility(JavaVisibility.PUBLIC);
        setOffset.setName("addCriterion");

        setOffset.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "sql"));
        setOffset.addBodyLine("super.addCriterion(sql);");
        criteria.addMethod(setOffset);
    }
}
