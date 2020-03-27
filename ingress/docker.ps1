npm run build
cd build
$d=get-date -format "yyyyMMddHHmm"
$name="pl-ingress:v"+$d
$reg="registry.cn-shanghai.aliyuncs.com/proglevel/"
$targetName=$reg+$name
docker build --build-arg ngconf=$args -t $name .
docker tag $name $targetName
# docker push $targetName
echo $targetName
cd ..