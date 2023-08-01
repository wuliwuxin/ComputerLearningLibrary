val test = sc.textFile("file:///root/experiment/scores.txt")
val total=test.map{x=>val line=x.split(",");(line(0))}
total.distinct.count
val course=test.map{x=>val line=x.split(",");(line(1))}
course.distinct.count
val av=test.map{x=>val line=x.split(",");(line(0),line(1),line(2).toInt)}
val score=av.filter(x=>x._1=="Tom").map(x=>x._3)
score.mean
val me_score=av.filter(x=>x._1=="Wuxin").map(x=>x._3)
me_score.mean
val datardd=test.map{x=>val line=x.split(",");(line(0),line(1),line(2).toInt)}
val data=datardd.filter(x=>x._2=="DataBase").map(x=>x._1)
data.distinct.count
val course=test.map{x=>val line=x.split(",");(line(0),line(1))}
val number=course.groupByKey()
number.map(x=>(x._1,x._2.size)).collect
val av_scores=test.map{x=>val line=x.split(",");(line(1),line(2) .toInt)}
val score= av_scores.mapValues(x=>(x,1)).reduceByKey((x,y)=>(x._1+y._1,x._2+y._2)).mapValues(x=>(x._1.toDouble/x._2))
score.collect

val test = sc.textFile("file:///mycode/jc_content_viewlog.txt")
val allData=test.map{x=>x.split(",")}
val userID = allData.map(x=>(x(3),1))
userID.collect
val times = userID.reduceByKey((a,b)=>a+b) .filter(x=>x._2>50).keys.collect
val sum= allData.filter(x=> times.contains(x(3)))
sum.collect
val web= sum.map(x=>(x(2),1))
val webtimes = web.reduceByKey((a,b)=>a+b)
val num_sort= webtimes.sortBy(x=>x._2,false)
num_sort.take(5)
