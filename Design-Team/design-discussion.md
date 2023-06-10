
# Design1

![Design1](https://user-images.githubusercontent.com/77685562/112787737-3a3afa00-9027-11eb-86a3-21178821d556.png)
pros: item class was well designed
cons: some classes were not needed, and some operations for calculating items were not needed either
# Design2
![Design2](https://user-images.githubusercontent.com/77685562/112787942-bd5c5000-9027-11eb-9e27-fc43dba7ab47.png)
pros: itemType and item were well organized and contained the necessary attributes and operations
cons: checkedOff items class was not necessary, could have been added to groceryList
# Design3
![Design3](https://user-images.githubusercontent.com/77685562/112788099-162be880-9028-11eb-9f35-5b88af3252fa.png)
pros: user design was well implemented but not needed, also had the right idea of what classes were needed
cons:checked off items class is unecessary aswell as user class. Also multiplicity value's were not clear, only some
# Design4
![Design4](https://user-images.githubusercontent.com/77685562/112787996-debd3c00-9027-11eb-92d1-9fc97444a735.png)
pros: Had grocerylist manager for mulitple list and useful attributes ad operations
cons: Database was meant to be itemType, and user class was not necessary
# Design5
![Design5](https://user-images.githubusercontent.com/77685562/112788022-ed0b5800-9027-11eb-889c-8f173fc0961c.png)
pros: useful classes, attributes and operations, checked items is under grocery list instead in it's own separate class
cons: multiplicity values were a bit off, user was not needed, and missing a grocerylist manager to hold multiple list


# Team Design
![Team Design](https://user-images.githubusercontent.com/35271372/112788927-f39acf00-9029-11eb-83b5-d8a937016f9c.png)
The commonalities between the team design and individual designs are:
* There is an **Item**, **ItemType**, and **GroceryList** class
* User are able to **addItem()**, **removeItem()**, **addCategory()**, **removeCategory()**, **createList()**, 
**modifyList()**, **deleteList()**, **updateQuantity()**, **clearCheck()**, and **checkOff()**.

The differences between the team design and individual designs are:
* The team design has a **GroceryListManager** class that manages all Grocery List
* **CheckOff** methods is no longer part of another class and **CheckOff** no longer an individual class since it is redundant to 
have seperate methods in another class rather than to have the checkoff methods within the **GroceryList** class. Methods to check 
items off is implemented within **GroceryList** class.
* **User** class is removed because the design document does not specify the need for **User** class.
* The relationship between **Item** has changed **ItemType**. **Item** inherits **ItemType**.
* **Item** class has get/set methods for name, type, and quantity for the sake of accessing **Item** class attributes. 
Attribute **check** was added to **Item** class to track which item was checked off.
* **GroceryList** class defined as an aggregation of **Item** class with a 1 to many relationship because in a GroceryList, 
there can be multiple items.
* **GroceryListManager** class is an agggregation of **GroceryList** class with a 1 to many relationship, because in a 
GroceryListManager, there can be many lists that it manages.


# Summary

In the group discussion, we discussed closely which class we believed were necessary to include in our UML diagram and the relationship between 
different classes. We realize that almost everyone has a User class in our own individual projects, and we realize we actually don’t need the user class. 
But fortunately, everyone’s project had an something that was useful to include in our final team project. We simply extracted some parts of our individual 
projects and implemented it in a new UML design. In the process of redesigning, everyone expressed their opinions and learned alot from each other. 
Everyone seemed to have the right understanding of the relationship between classes, but we did run into some unnecessary classes on our individual 
projects. We believe that working on this group project, we manage to help each other understand the project better, understand how to build a clear UML and improve what we did wrong.
