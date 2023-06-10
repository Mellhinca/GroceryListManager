 ​GroceryListManager
​

1. First of all, a class needed to be added that will have all of the items choosed by
   at least one user.
  
   Class Name: itemList
  
   Attributes: listId{PK}, quantity

   Operations: addItem, removeItem, updateQuantity

2. We need to create a class for this requirment.

   Class Name: item

   Attributes:itemId{PK}, itemName, itemPrice

   Operations:addItem, changeItem, updatePrice

3. By requirment, we need to create a class.

   Class Name: user

   Attributes: userId{PK}, userName(firstName, lastName), address, phoneNumber

   Operations: addUser, deleteUser, updateUser, modifyUser

     This class should have a relationship with class itemList, 
   Users must be able to add items to a list by picking them from a hierarchical list,
   add item, delete item and  able to specify a quantity for that item.

4. No new class needed. if user search a item by name, messages will be send to DB and
   retrun the result for user.

5. No new class needed. This requirement is regarding saving the ItemsList implemented as a design of Requirement1.

6. New class needed.

   Class Name: checkOffItems

   Attributes: listId{PK}, itemId, quantity

   Operations: addCheckOff, removeCheckOff, saveAutomaticlly

   Users are able to check off items in a list and it's related with class user.

7. New operation needed for existing class checkOffItem.

   Class Name: checkOffItems

   Operations: removeCheckOff

   Users are able to clear all the check-off marks in a list at once.

8. Another new operation needed for existing class checkOffItem.

   Class Name: checkOffItems

   Operations: saveAutomaticlly

   Check-off marks for a list are persistent and will be saved immediately.

9. New class needed.

   Class Name: groceryList

   Attributes: groceryId{PK}

   Operations: addGrocery, removerGrocery, updateGrocery

     It is related with class item, cause items should be a part of grocery list.
   The application will present the items in a list grouped by groceryList, so as to allow users to
   shop for a specific type of products at once.

10. This requirement is a relationship between User and ItemsList classes where one User will have multiple ItemsList.
    One User class instance will have multiple ItemsList.

11. This requirment is not considered, it's not a part of our design.





    SHENGWEI MA
    23387179
