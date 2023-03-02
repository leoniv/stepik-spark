import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.{types => ST}

object main {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder()
      .appName("Playground")
      .master("local")
      .getOrCreate()

    import spark.implicits._

    val restaurantExSchema = ST.StructType(
      Seq(
        ST.StructField("has_online_delivery", ST.IntegerType, true),
        ST.StructField("url", ST.StringType, true),
        ST.StructField(
          "user_rating",
          ST.StructType(
            Seq(
              ST.StructField("rating_text", ST.StringType, true),
              ST.StructField("rating_color", ST.StringType, true),
              ST.StructField("votes", ST.StringType, true),
              ST.StructField("aggregate_rating", ST.StringType, true),
            )
          ),
          true,
        ),
        ST.StructField("name", ST.StringType, true),
        ST.StructField("cuisines", ST.StringType, true),
        ST.StructField("is_delivering_now", ST.IntegerType, true),
        ST.StructField("deeplink", ST.StringType, true),
        ST.StructField("menu_url", ST.StringType, true),
        ST.StructField("average_cost_for_two", ST.IntegerType, true),
      )
    )

    val df = spark.read
      .schema(restaurantExSchema)
      .json("src/main/resources/1_df_files/restaurant_ex.json")

    df.show()
    df.map(x =>
      x.getAs[Int]("has_online_delivery") + x.getAs[Int]("is_delivering_now")
    )
    df.printSchema()

    spark.stop()
  }
}
