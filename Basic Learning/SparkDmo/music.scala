val sqlContext=new org.apache.spark.sql.SQLContext(sc)
case class UserArtist(userid:Int,artistid:Int,count:Int)
val user_artist_data=sc.textFile("file:///mycode/user_artist_data.txt").map{x=>val y=x.split(" ");UserArtist(y(0).toInt,y(1).toInt,y(2).toInt)}.toDF()
user_artist_data.registerTempTable("user_artist")
sqlContext.sql("select count(distinct userid) from user_artist").show()
sqlContext.sql("select userid,count(distinct artistid) from user_artist group by userid")show()
sqlContext.sql("select artistid,count from user_artist where userid=1000002 order by count desc limit 10").show()

