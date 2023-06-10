1. A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity
of items in the list,and change the quantity of items in the list (e.g., change from one to two pounds of apples). 
To make this happen I've create a class call User that have method that allow user to add item,
delete item, and change the quantity of the item list.

2. The application must contain a database (DB) of items and corresponding item types. 
To satisfy this requirement I added to the design a class call item and item type that relates to the item.

3. Users must be able to add items to a list by picking them from a hierarchical list, where
the first level is the item type (e.g., cereal), and the second level is the name of the
actual item (e.g., shredded wheat). After adding an item, users must be able to specify a
quantity for that item. 
To make this happen I added a attribute name for both item and itemType class, this way user
can identify from each hierarchical list. Additionally I added an attribute quantity and a method call updateQuantity() for user to specify the amount added to the list.

4. Users must also be able to specify an item by typing its name. In this case, the
application must look in its DB for items with similar names and ask the users, for each
of them, whether that is the item they intended to add. If a match cannot be found, the
application must ask the user to select a type for the item and then save the new item,
together with its type, in its DB. 
To satisfy this condition I've added method for item call additem() to encount user adding item to the list, as for item Type 
I added a method called updateItem() incase user have to select a type for the new added item.

5. Lists must be saved automatically and immediately after they are modified.
When calling the methods in Items List everything is saved and update immediately.

6. Users must be able to check off items in a list (without deleting them). 
To make this work I've created another class that mirror the items List that just act like a
temporary list that user can check off items without actually deleting the item on user list.

7. Users must also be able to clear all the check-off marks in a list at once. 
To implement this I've created a method called clearCheckOff() where the method clear out
all check off mark from the check off item list.

8. Check-off marks for a list are persistent and must also be saved immediately. 
Similarly to Items list methods, all the methods called in Check Off Items are saved immediately.

9. The application must present the items in a list grouped by type, so as to allow users to
shop for a specific type of product at once (i.e., without having to go back and forth
between aisles). 
Since I've created the Item type class, user can easily access the DB by looking through item type attribute call name.

10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly
farmer’s market list”). Therefore, the application must provide the users with the ability to
create, (re)name, select, and delete lists. 
To make this work I've implemented three method createList(), modifyList(), deleteList() to provide user capabilities to modify
or have multiple list in one account.

11. The User Interface (UI) must be intuitive and responsive. 
This is not considered because it does not affect the design directly.

