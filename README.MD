# JPA Entities in Kotlin
This repository was originally used in the "[Getting the Most from JPA with Kotlin](https://www.youtube.com/watch?v=a_6V8xwiv04)" webinar.
To follow along, checkout the "[webinar-starting-point](https://github.com/jpa-buddy/kotlin-entities/tree/webinar-starting-point)" branch.

##How to define entities in Kotlin
1. Use the [*all-open*](https://kotlinlang.org/docs/all-open-plugin.html) compiler plugin or
   explicitly define **all** entity classes and properties as `open`.
    
2. Use the [*kotlin-jpa*](https://kotlinlang.org/docs/no-arg-plugin.html#jpa-support) compiler plugin or
   make sure to always have a **no-args constructor** in entity classes.
   
3. To use data classes, ALWAYS:
   1. Use all-open and kotlin-jpa plugins;
   1. override `equals()` to use only the id;
   2. override `hashCode()` to return:
      1. a constant value if the id is generated by the DB;
      2. `id.hashCode()` if it's a natural or client-generated id.
   3. override `toString()` to exclude all lazy fields.

Read more about `equals()` and `hashCode()` implementation in [this article](https://thorben-janssen.com/ultimate-guide-to-implementing-equals-and-hashcode-with-hibernate/). While implementing them is optional for regular entities, it is necessary for data classes.

Find examples of defined entities in the [domain.kt](src/main/kotlin/com/jpabuddy/kotlinentities/domain.kt) file.