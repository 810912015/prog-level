package com.pl.all;

import com.pl.admin.AdminApplication;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.StringUtils;

@SpringBootApplication(scanBasePackages = "com.pl")

@ComponentScan(nameGenerator = AllApplication.SpringBeanNameGenerator.class)
//重点
@ServletComponentScan
@EnableCaching
public class AllApplication {
    public static class SpringBeanNameGenerator extends AnnotationBeanNameGenerator {
        @Override
        protected String buildDefaultBeanName(BeanDefinition definition) {
            if (definition instanceof AnnotatedBeanDefinition) {
                String beanName = determineBeanNameFromAnnotation((AnnotatedBeanDefinition) definition);
                if (StringUtils.hasText(beanName)) {
                    // Explicit bean name found.
                    return beanName;
                }
            }
            return definition.getBeanClassName();
        }
    }
    public static void main(String[] args) {
        SpringApplication.run(AllApplication.class, args);
    }
}
