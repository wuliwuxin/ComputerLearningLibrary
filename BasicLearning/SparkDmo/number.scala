import scala.io.StdIn._
var q = readDouble()
var sum = 0.0
var i = 1
if(q>0){
while( sum < q){
    sum += (i+1)/i.toDouble
    i += 1
}
println(sum.formatted("%.6f")) 
}else{
println("erro!")
}


