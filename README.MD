# JPA Entities in Kotlin
This repository was originally used in the "[Getting the Most from JPA with Kotlin](https://www.youtube.com/watch?v=a_6V8xwiv04)" webinar.
To follow the webinar flow step by step, checkout the "[webinar-starting-point](https://github.com/jpa-buddy/kotlin-entities/tree/webinar-starting-point)" branch and click on the image below to start watching.

[![Watch the video](https://img.youtube.com/vi/a_6V8xwiv04/hqdefault.jpg)](https://www.youtube.com/watch?v=a_6V8xwiv04)

## JPA Entities on Kotlin Checklist
- To avoid significant performace issues and enable lazy loading for _Many/One to One_ assosiations make sure you mark all JPA-related classes and their properties as `open`.
```
@Table(name = "project")
@Entity
open class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Column(name = "name", nullable = false)
    open var name: String? = null

    ...
}
```
- You may use the [*all-open*](https://kotlinlang.org/docs/all-open-plugin.html) compiler plugin to make all JPA-related classes and properties `open` by default. Make sure you configure it right, so it applys for all classes annotated as `@Entity`, `@MappedSuperclass`, `@Embeddable`. 
```
<compilerPlugins>
   ...
   <plugin>all-open</plugin>
   ...
</compilerPlugins>
<pluginOptions>
   <option>all-open:annotation=javax.persistence.Entity</option>
   <option>all-open:annotation=javax.persistence.MappedSuperclass</option>
   <option>all-open:annotation=javax.persistence.Embeddable</option>
</pluginOptions> 
```
- Using primary constructors in JPA-related classes will cause the following exception `org.hibernate.InstantiationException: No default constructor for entity`. To resolve this issue you may manually define no-args constructor or use the [*kotlin-jpa*](https://kotlinlang.org/docs/no-arg-plugin.html#jpa-support) compiler plugin, which ensures that no-args constructor will be generated in bytecode for each JPA-related class.
```
<compilerPlugins>
   ...
   <plugin>jpa</plugin>
   ...   
</compilerPlugins>
```
-  To use data classes 
   * Enable the all-open plugin as it was described above, because this is the only way to make data classes `open` in the compiled bytecode.
   * Override `equals()`, `hashCode()`, `toString()` in accordance with one of this articles by [Vlad Mihalcea](https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/) or [Thorben Janssen](https://thorben-janssen.com/ultimate-guide-to-implementing-equals-and-hashcode-with-hibernate/).
- (JPA Buddy)[https://plugins.jetbrains.com/plugin/15075-jpa-buddy] is aware of all these things and always generate valid entities for you, including extra stuff like `equals()`, `hashCode()`, `toString()`!

Find more examples of JPA entities on Kotlin in the [domain.kt](src/main/kotlin/com/jpabuddy/kotlinentities/domain.kt) file. It also includes detailed explanation of the different use cases inlined in the comments. 
