# Jackson serializer - deserializer for JsonApi

This project can be use to serialize and deserialize JsonApi object format.

State: **Stable**


## Technical info

Technical stack:
- Scala 2.12
- Jackson 2.9.2
- Jackson Scala module 2.9.2

### Entity Annotation

To work, this module use three annotations:
   - JsonApi
   - JsonApiId
   - JsonApiRelationShip
   - JsonApiMeta

Example of entity:

``` scala

@JsonApi(apiType= "person")
class Person() {

  @JsonApiId
  // can be UUID
  var id: String = _

  var name: String = _

  @JsonApiRelationShip
  var childs: List[Person] = _

}

```

This entity will produce a JSON like this:

``` json
{
  "data": {
    "type": "person",
    "id": "fatherDoe",
    "attributes": {
      "name": "Doe"
    },
    "relationships": {
      "childs": [{
        "data": {
          "id": "jhonDoe",
          "type": "person"
        }
      }]
    }
  }
}
```

We can use case class like this
``` scala

@JsonApi(apiType= "person")
case class Person(
                    @(JsonApiId @field)
                    id: String,
                    name: String,
                    @(JsonApiRelationShip @field)
                    childs: List[Person]
                    ) {
    def this(id: String) = this(id, None.orNull, None.orNull)
}
```
To work properly relationship object must have a constructor with only id as parameter. A relationship property can be an `Option` or an `java.util.Optional`

If you want add metadata on your objects, you should use `@JsonApiMeta` annotation.
The fields which have this annotation will be added in the `meta` property.

``` scala
@JsonApi(apiType = "classWithOptionMetaData")
case class ClassWithOptionMetaData(
                                    label: String,
                                    @(JsonApiId@field)
                                    id: String,
                                    @(JsonApiMeta@field)
                                    foo: Option[String],
                                    @(JsonApiMeta@field)
                                    bar: Option[Bar]
                                  )

case class Bar(test: String, testList: List[String])
```

If the meta fields are provided, the json ouput will be like this:

``` json
{
  "data": {
    ...,
    "meta": {
      "foo": "fooTest",
      "bar": {
        "test": "barTest",
        "testList": ["barTestItem"]
      }
    }
  }
}
```

The meta fields with a value `None` or `null` are not added in the `meta` property.
If all meta fields have a value `None` or `null` then the `meta` property is not added in the json output.

You can use `@JsonProperty` to rename each property on JSON.

### Relationship link

The module can generates automatically the link of relationship when we serialize (see configuration in section 'Enable Module').
If a jsonAPI resource type isn't defined in link configuration, the generation of link is by-passed.

### Enable Module

To enable this module you should to add `JsonApiModule` on objectMapper`

``` scala
import com.norauto.api.jsonapi.jackson.JsonApiModule

objectMapper.registerModule(JsonApiModule())
```

You can specify the configuration for generating link of relationship. If no configuration is provided, no link will be automatically adding.

```scala
import com.norauto.api.jsonapi.jackson.JsonApiModule
val linker = new JsonApiRelationshipLinkContext(Map("testIdString" -> "http://api.norauto.com/organizationalunitservice/testIdString"))
objectMapper.registerModule(JsonApiModule(linker))
```

### Use list

If you want serialize a list, you should use `JsonApiList` class

``` scala
import com.norauto.api.jsonapi.domain.JsonApiList

val personList: List[Person] = Nil

val listToSerialize = JsonApiList(personList)
```

If you want use a paginated list, you should define two implicit class `CanBePaginated` and `CanBeQueryInfo`
This is an example for Spring

``` scala

import javax.servlet.http.HttpServletRequest

import com.norauto.api.jsonapi.domain.{CanBePaginated, CanBeQueryInfo, QueryInfo, Page => JsonApiPage}
import org.springframework.data.domain.Page

import scala.collection.JavaConverters._

trait SpringPaginator {

  implicit val pageToJsonApiPage: CanBePaginated[Page] = new CanBePaginated[Page] {
    case class SpringJsonApiPage[T](number: Int, size: Int, totalElements: Long, totalPages: Int, content: List[T]) extends JsonApiPage[T]

    override def toPage[T](springDataPage: Page[T]): JsonApiPage[T] = SpringJsonApiPage(
      springDataPage.getNumber + 1,
      springDataPage.getSize,
      springDataPage.getTotalElements,
      springDataPage.getTotalPages,
      springDataPage.getContent.asScala.toList
    )
  }

  implicit val httpServletRequestToQueryInfo: CanBeQueryInfo[HttpServletRequest] = new CanBeQueryInfo[HttpServletRequest] {
    case class SpringQueryInfo(requestURL: String, queryString: String) extends QueryInfo

    override def toQueryInfo(servletRequest: HttpServletRequest): QueryInfo = SpringQueryInfo(
      Option(servletRequest.getRequestURL).map(_.toString).orNull,
      servletRequest.getQueryString
    )
  }

}

```

### Use included resources

To manage included resource ([json api spec](http://jsonapi.org/format/#fetching-includes)) an class `JsonApiWrapper` has been created.

This class take two parameters:
    - the initial object to serialize
    - the include resource list

When this class is serialized, the `included` field is added on JSON.

On Spring, this is done automatically, if a `include` query param is detected `included` is automatically added.