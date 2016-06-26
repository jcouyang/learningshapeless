package learnshapeless

import shapeless._
import shapeless.tag.@@
import shapeless.test.illTyped

/** General Guidelines
  *
  * Definitons named `eg` are examples to learn from. Examples are numbered by the exercises they related to or inform.
  *
  * Exercises beginning with `ex` are for you to complete. Each exercise has a comment above describing the task.
  *
  * You can verify whether your exercise solution is correct by running the Part1HListTest in src/test/scala.
  *
  * Note: the file is runnable, so you can drop in println statements to look at the values of expressions
  *
  * */
object TaggedTypes extends App {

  case class EgWeaklyTypedPerson(id: String, name: String, age: Int, numberChildren: Int)

  def eg_person = EgWeaklyTypedPerson("KAT513436", "Katherine", 56, 2)

  def eg_personOops = EgWeaklyTypedPerson("Katherine", "KAT513436", 2, 56)

  trait IdTag
  type Id = String @@ IdTag

  //The `tag` operator provided by Shapeless doesn't work when the target def has an explicit type declared
  illTyped("""def eg_id1: Id = tag2[IdTag]("KAT513436")""")
  def eg_id: Id = tag2("KAT513436").@@[IdTag]

  trait NameTag
  type Name = String @@ NameTag
  /** Tag the string "Katherine" as a Name*/
  def ex_name: Name = tag2("Katherine").@@[NameTag]

  def eg_stillAString: String = ex_name

  /** Create 2 new type tags for Age and NumberChildren and tag2 `56` and `2` respectively */
  trait AgeTag
  type Age = Int @@ AgeTag
  def ex_age: Age = tag2(56).@@[AgeTag]

  trait NumChildrenTag
  type NumChildren = Int @@ NumChildrenTag
  def ex_numberChildren: NumChildren = tag2(2).@@[NumChildrenTag]

  case class EgTypedPerson(id: Id, name: Name, age: Age, numChildren: NumChildren)

  def eg_WontCompileMissingTags = illTyped("""EgTypedPerson("KAT513436", "Katherine", 56, 2)""")

  def eg_ShouldntCompileWrongData = illTyped("""EgTypedPerson(ex_name, eg_id, ex_numberChildren, ex_age)""")

  /** Create a typed person instance correctly passing tagged values `eg_id`, `ex_name`, ` ex_age`, `ex_numberChildren` */
  def ex_TypedPerson = EgTypedPerson(eg_id, ex_name, ex_age, ex_numberChildren)

}
