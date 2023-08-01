import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.Duration
import org.apache.spark.streaming.Seconds
sc.setLogLevel("WARN")
val ssc=new StreamingContext(sc,Seconds(5))  
val mingdan=sc.textFile("file:///mycode/mingdan.txt").map{x=>val y=x.split(" ");(y(0),y(1).toBoolean)}
val st=ssc.socketTextStream("localhost",8888)
val users=st.map(x=>(x.split(" ")(1),x))
val validRddDS=users.transform(x=>{
val joinRdd=x.leftOuterJoin(mingdan)
val fRdd=joinRdd.filter(y=>{if(y._2._2.getOrElse(false)){false}else{true}})
val validRdd=fRdd.map(y=>y._2._1)  
validRdd})
validRddDS.print()
ssc.start()

