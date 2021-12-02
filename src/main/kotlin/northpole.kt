import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.log
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt

/*
In this file you will find a list of all the cities that are planned to be visited this year.
The position of the cities is given in the format Well-Known Text, more specifically: Point (E, N).

All cities must be visited only once.
Santa has never quite managed to find a completely optimal route,
 so to decide which city he should move on to, he always chooses the nearest unvisited city on the list.

How far will Santa and the reindeer travel this year?
Enter the answer as an integer rounded to the nearest kilometer.

Assume that the journey starts and ends exactly at the pole point and that the earth's radius is 6371km.
 */
fun main() {
   // val dist = distance(latitude 59.911111111,59.329444444,10.752777777, 18.068611111)
    //println(dist)
    findShorestDistanceFromCurrentLocationToNext()
}

/**
 * find shortest distance from current position to next city
 */
fun findShorestDistanceFromCurrentLocationToNext() {
    val list = readLines("src/main/kotlin/cities.csv")
    val split = list.map { it.split(",") }
    val coordnates = split.map { it[1].split(" ") }
    var citiesAndCoords = split.mapIndexed { index, list ->
      CityWithCoordinate(list[0], coordnates[index][0].toDouble(),coordnates[index][1].toDouble(),0.0)
    }.toMutableList()
    var currentCity = citiesAndCoords.first();
    var totalDistance = 0;
    var visited = mutableListOf<CityWithCoordinate>()

    while (visited.size < citiesAndCoords.size) {
       // if (!visited.contains(currentCity)){
           // var index = citiesAndCoords.indexOf(currentCity)
           // citiesAndCoords.removeAt(index)
            visited.add(currentCity)
            val dist = calulateShortestDistancefromCurrentCityToAllCities(currentCity, citiesAndCoords)
            currentCity = dist
            totalDistance += dist.distance.roundToInt()
        }
    println(totalDistance)
totalDistance += distance(currentCity.latitude, 90.0000, currentCity.longitude, 135.0000).roundToInt()
    var x = totalDistance
    println(x)
 //   }

}

/**
 * compare distances between current city and all other cities, and return the shortest distance
 */
fun calulateShortestDistancefromCurrentCityToAllCities(currentCity: CityWithCoordinate, cities:
List<CityWithCoordinate>): CityWithCoordinate {
    var shortestDistance = Double.MAX_VALUE
    var shortestCityName = ""
    var longitude = 0.0
    var latitude = 0.0

    cities.forEach {
        if (!it.visited && currentCity.name != it.name) {
        val distance = distance(currentCity.latitude, it.latitude, currentCity.longitude, it.longitude)
        if (shortestDistance > distance ) {
            shortestDistance = distance
            shortestCityName =it.name
            longitude = it.longitude
            latitude = it.latitude

        }
        }
    }
    cities.forEach {
        if (it.name == shortestCityName) {
            it.visited = true
        }
    }
    println("name $shortestCityName, $shortestDistance" )
    return CityWithCoordinate(shortestCityName,longitude,latitude, shortestDistance,visited = true)
}



fun distance(lat1: Double, lat2: Double, lon1: Double , lon2:  Double): Double {

    // The math module contains a function
    // named toRadians which converts from
    // degrees to radians.
    val lon1 = Math.toRadians(lon1);
    val lon2 = Math.toRadians(lon2);
    val lat1 = Math.toRadians(lat1);
    val lat2 = Math.toRadians(lat2);

    // Haversine formula
    var dlon = lon2 - lon1;
    var dlat = lat2 - lat1;
    var a = sin(dlat / 2).pow(2.0) + cos(lat1) * cos(lat2) * sin(dlon / 2).pow(2.0);

    var c = 2 * asin(sqrt(a));

    // Radius of earth in kilometers. Use 3956
    // for miles
    var  r = 6371;

    // calculate the result
    return (c * r)
}
class CityWithCoordinate(var name: String, var longitude: Double, var latitude: Double, var distance: Double, var visited: Boolean = false)
{ }
// driver code

class CityAndDistance(var name: String, var distance: Int) { }