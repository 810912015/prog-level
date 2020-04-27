mvn clean package
$baseDir="d:\proj\prog-level\"
$names="portal","search","admin"
$reg="registry.cn-shanghai.aliyuncs.com/proglevel/"
$d=get-date -format "yyyyMMddHHmm"
for($i=0;$i -lt 3;$i++){
   $nt=$names[$i]
   $iname=$nt+$d
   cd $baseDir$nt
   docker build -t $iname .
   $dt=$reg+"pl-"+$nt+":v"+$d
   docker tag $iname $dt
   # docker push $dt
   echo $dt
}
cd ..
