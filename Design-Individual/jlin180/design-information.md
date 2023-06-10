# Design Information

1. A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).
	* To realize this requirement, I added to the design a **GroceryList** class with a List attribute containing Item objects. The class contains methods *addItems(items,quantity)*, *deleteItems(items)*, *changeItemQuantity(quantity)*, *clearAllCheckMarks()*, and *save()*.
	* I also added to the design a **User** class and a **Items** class. 
	* In the **User** class, I assumed each user has a "username" and "password" credentials, but the application does not require these attributes to have.
2. The application must contain a database (DB) of items and corresponding item types .
	* To realize this requirement, I created a **Database** class to represent methods that accesses the database which also allows other classes to have access such as *getItemsByType(type)*, *getItemTypes()*, *insertItem(type,name)*, and *searchByItemName(name)*
3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.
	* To realize this requirement, I added methods *getItemsByType(type)* and *getItemTypes()* to the **Database**. *getItemsByType(type)* is supposed to access the **Database** and return a list of items identified under the item type. *getItemTypes()* is supposed to access the **Database** and return what categories of item types are available. I assumed when *displayItemTypes()* and *displayItemNamesByType(type)* in **GroceryListManager** class to display the returned list from *getItemType()* and *getItemsByType(type)* respectively. I also added *addItems(item,quantity)* method in **GroceryList** for when the user adds an item to the list.
	* I assume in the *addItems(item,quantity)* method, *setItemQuantity(number)* and *setItemName(name)* in **Items** class will be called. 
4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB.
	* To realize this requirement, I added the method *searchByItemName(name)* in the **Database** class and the method in *searchItem(name)* the **GroceryListManager** class. I assume that in the *searchItem(name)* method, it will call upon *searchByItemName(name)* method to find items similar to the one that the user is searching for. If not, the user will be prompted to add the item and its type through *insertItem(type,name)* method which will also be called within *searchItemByName(name)* method.
5. Lists must be saved automatically and immediately after they are modified.
	* To realize this requirement, I added a method called *save()* in **GroceryList** class to save any changes made in the list.
6. Users must be able to check off items in a list (without deleting them).
	* To realize this requirement, I added a boolean attribute called "CheckedOff" in the **Item** class.
	* I also added *setItemCheckedOff(checkOff)* and *getItemCheckedOff()* methods to **Item** class.
7. Users must also be able to clear all the check-off marks in a list at once.
	* To realize this requirement, I added a *clearAllCheckMarks()* method in the **GroceryList** class which will call upon *setItemCheckedOff(checkOff)* method
	* I also added *checkAllCheckMarks()* method to allow the user to check all items at once which will call upon *setItemCheckedOff(checkOff)* method as well.
8. Check-off marks for a list are persistent and must also be saved immediately.
	* To realize this requirement, *setItemCheckedOff(checkOff)* method will be called to set the attribute "CheckedOff" of an item either true or false.
9. The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of product at once (i.e., without having to go back and forth between aisles).
	* To realize this requirement, I created a **groupByType()** method to the **GroceryList** class that presents items in a list grouped by their type.
10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.
	* To realize this requirement, I created a **GroceryListManager** class and added methods *createList()*, *renameList()*, *deleteList()*, and *selectList()*.
11. The User Interface (UI) must be intuitive and responsive.
	* Not considered because it does not affect the design directly.