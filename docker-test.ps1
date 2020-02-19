mvn clean package
$baseDir="d:\proj\rb\"
$names="reader-admin","reader-portal","reader-search"
$reg="192.168.1.230:5000/"
$d=get-date -format "yyyyMMddHHmm"
for($i=0;$i -lt 3;$i++){
   $nt=$names[$i]
   $iname=$nt+$d
   cd $baseDir$nt
   docker build -t $iname .
   $dt=$reg+$nt+":v"+$d
   docker tag $iname $dt
   docker push $dt
   echo $dt
}
cd ..
