# Use Case Model V1

**Author**: Team JMASK

## 1 Use Case Diagram

![UseCaseDiagram](https://user-images.githubusercontent.com/77685562/112871719-8c166b00-908d-11eb-97b9-b3457f1120a1.png)  

## 2 Use Case Descriptions

**Change quantity of item** </br>
-Requirements: Use case "change quantity of item" allows the user to change the amount of the item they added to their list. </br>
-Pre-Conditions: In order to run this case, an item type must have been selected, then the item must have been selected and added to a list. If there is no item, then no change of quantity can be done. </br>
-Post-Conditions: Once the case is run, the quantity amount related to the item selected will be different, depending on what the user inputs. </br>
-Scenarios: 
<ul>
    <li>Select item type</li>
    <li>Select item(if item not found, add new item)</li>
    <li>Add item to list</li>
    <li>Change Quantity of Item Selected</li>
</ul>
</br>

**Search Item From Hierarchical List** </br>
-Requirements: The use case will allow the user to search an item type, and after selecting an item type, the user can search and item.</br>
-Pre-Conditions: There is no other case to be run before the "Search Item From Hierachical List" case. This is the first case the user will run into. </br>
-Post-Conditions: After the "Search Item From Hierarchical List" case, the user will have an Item Type selected and an Item selected, allowing the user to continue to adding the item to their list. </br>
-Scenarios: 
<ul>
    <li>Select Item type</li>
    <li>Select item</li>
</ul>
</br>

**Search item by name, add item if not found** </br>
-Requirements: After selecting the item type, the user moves on to selecting an item. The user may begin to search for the item they want, or also type the item they are looking for. If there is no match, the user can add this item. This use case allows the user to search for the item, or add the new item. </br>
-Pre-Conditions: There must be an item type selected prior to searching for an item. </br>
-Post-Conditions: The user will have an item type and an item selected after this use case. </br>
-Scenarios: 
<ul>
    <li>Select Item type</li>
    <li>Select item(if not found, add new item)</li>
</ul>
</br>

**CheckOff** </br>
-Requirements: The use case "CheckOff" allows the user to check off items in their list, allowing the user to keep their list more organized as they are doing their grocery shopping. This use case also allows the user to clear off all check marks from a list without deleting the items they have in their list. </br>
-Pre-Conditions: In order to run the Check Off use case, there must be items present in the users list. </br>
-Post-Conditions: Once the Check Off case has been used, there should be items the user wants to set as checked, with a check mark. </br>
-Scenarios:
<ul>
    <li>Select Item type</li>
    <li>Select item(if not found, add new item)</li>
    <li>Add item to list</li>
    <li>Change quantity of item</li>
    <li>Add list to Grocery List manager</li>
    <li>Check off desired items</li>
</ul>
</br>

**Grocery List Manager** </br>
-Requirements: Grocery List Manager gives the user the option of holding many list at once, allowing the user organize their grocery shopping list and making it easier to manage.</br>
-Pre-Conditions: Prior to the Grocery List Manager, the user needs to have all items chosen and added to a list.</br>
-Post-Conditions: After Grocery List Manager, the user will have all the list they created, and will be allowed to open any list without affecting the other list.  </br>
-Scenarios: 
<ul>
    <li>Select Item type</li>
    <li>Select item(if not found, add new item)</li>
    <li>Add item to list</li>
    <li>Change quantity of item</li>
    <li>Add list to Grocery List manager</li>
</ul>








