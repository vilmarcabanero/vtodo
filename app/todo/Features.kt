Existing Features from Philipp

/* Existing Features
done TODO
EF1
As a user, I can see the empty main screen with floating action button at the bottom right.
- initially when there are no todos, main screen is empty. just the floating action.
- when there are todos available, the main screen will now contain the list of todos which is for EF2

done TODO
EF2
As a user, I can see the main screen with list of todos I make

done TODO
EF3
As a user, when I click the + floating action button, I will be redirected to CreateEditTodo screen

done TODO
EF4
As a user, when I am in the CreateEditTodo screen with empty fields, I can type the title (required) and description (optional) of todo_
and when I click the check floating action at the bottom right,
I want a new todo_ to be created, and I will be redirected to the main screen
and the todo_ I created will be displayed in the main screen (refer to EF2 to display the todos)

done TODO
EF5 (new feature)
As a user, when I am in the main screen and there is at least one todo_,
when I touch the check box at the right, the todo_ will be toggled as complete, and the title and description will be underlined

done TODO
EF6
As a user, when I click the delete icon, the todo_ will be deleted,
and a snack bar will appear,
and if I click the undo text, the todo_ will be restored,
and if I don't click the undo text, the todo_ will be deleted permanently.
*/

/*
New Features
done TODO
F1
As a user, when I click the todo_, I will be redirected to CreateEditTodo screen and the title and description text fields will be
populated by the todo_'s existing title and description.

done TODO
F2
As a user, when I am in CreateEditTodo screen, and when I edit the text fields, and when I click the check floating icon button,
the todo_ will be saved and the new todo_ details will reflect to the list of todos in main screen.
and I will be redirected back to the main screen.

done TODO
F5
As a developer, I want the to make the focus state of title textfield to true when I navigate to AddEditTodoScreen.

done TODO
F6
As a developer, I want to remove the delete icon from the todo_ list, and move it to the AddEditTodoScreen instead.
- at the top right of the screen, have a vertical more icon, then a pop up will appear with a menu item of "Delete"
- which is when clicked, the todo_ will be deleted and the user will be redirected to the TodoListScreen.

done TODO
F7
As a developer, I want to maintain the order of todos before and after I edit and delete a todo_
- current behaviour is that the new todo_ will always be put at the last of the list
- possible solution, add a createdAt field, and then always arrange the todo_ by date, from latest to oldest.

done TODO
F8
As a developer, I want to remove the extra space for description when description is null or empty.
- current behaviour is that the description always contains vertical space.

done TODO
F9
As a developer, I want to maintain the two lines for description in TodoListScreen, with ellipsis.

done TODO
F10
As a developer, I want the id as the screen path params instead of the whole todo_ when a todo_ is clicked
- current implementation is that the todo_ is hinahanap muna ang todo_ then buong todo_ ang pinapasa as param.
- fixed the performance issue. buong todo_ parin pinapasa pero hindi na nagkakaroon ng search task. may copy na pala ng todo_.

done TODO
F11
As a developer, I want to trigger the create todo_ functionality when the return key on keyboard is clicked.
- make the return key as check.
- also programmatically hide the keyboard after return key is clicked since if di ginawa, medyo delay yung pag hide ng keyboard.
Nauna na mapunta sa TodoListScreen tas saka lang ma hide ang keyboard.
done TODO
F13
As a developer, I want the app to use BasicTextField instead of TextField
- so that, we can avoid the default Material UI TextField that has border bottom
- check if this looks more beautiful. If not, revert.

done TODO
F14
As a developer, I want the width of the DropdownMenu become 1/2 of the screen.

done TODO
F16
As a developer, I want to rename the Todo_ model to Task model. Also the other files such as classes.
- to match the remote entity. also, para di na need maglagay ng _ like for example Todo_

done TODO
F18
If I press the return key, when title is empty, the keyboard should not be hidden.

done TODO
F19
Disable the check icon when title is still empty or blank.

done TODO
F15
As a developer, I want to include userId for Todo_ model.
- for newly installed app and which the user is not yet logged in, create a default userId userCreatedAt for the user and store that
in SharedPreferences with userId and userCreatedAt key.
- when a user creates a task, the newly created task will have a userId field.
- note: userId > for new Task. userCreatedAt > for login? / register

 */

/* Bugs
done TODO
B1
When I delete a todo_, and undo it, snack bar will always appear everytime I go back to the TodoListScreen.
- fixed using SharedPreferences

done TODO
B2
When I click a complete todo_, then press check to save even if without editing, the isComplete status becomes false.
- the correct behaviour should remain the isComplete status of the todo_.

 */


TODO
F3
As a user, I want to have an online account.
So that when I user another device, my data will be backed up.
So that when I use more than one device, my data will be synced with all my devices.
- try, when app first loads, check if user is online. If he is, compare the data between remote and local, use versionKey as reference,
if versionKeys are different, it means that the data was updated. Compare each item, if one exists remotely, insert it in local.
If one exists locally, insert it in remote db.

id, title, description, isComplete, createdAt, versionKey, userId

TODO
F4
As a user, I want that my details be saved locally after I login, so that when I open the app even offline,
my details e.g. first name, email, etc. is visible on the app.
- save the user details in user table similar to user table in the backend.
and retrieve the user details thru query SELECT * FROM user WHERE id = :id

TODO
F11
As a developer, I want to make CreateTodoScreen separate but not a screen but a bottom drawer
- similar to google tasks

TODO
F12
As a developer, I want to make this app a Daily Tasks, in which the main list is the current todos of the day
- that is, when the day becomes tomorrow, the todos for yesterday will be moved at the bottom with Inactive (2) toggle
- the current todos of the day is Active todos
- similar to googles tasks "Completed (2)" where 2 is the number of completed todos, or in this app, the number of Inactive todos.

TODO
F17
- when user decided to register, the userId saved locally will be used as the id of the user data to be saved in the remote database.
- what will happen if the user has used the app for more than one device? There will be more than one userId saved locally.
Try, create a field userCreatedAt.
- when user created a todo locally from device A, it has userId-A. When he decided to register, the userId-A will be saved on the remote database,
along with userCreatedAt-Date-A, also the todo A. When he decided to use device B, another userId, userId-B will be created along with userCreatedAt-Date-B.
Now that user decided to create a todo B on device B, its userId is userId-B. When he decided to log in to sync his data from the remote data,
there would be a conflict. To solve this, when he decides to log in, update the userId to userId-A, then that userId is now stored in jwt to be sent by
login response. Also, when background tasks is running to sync the data, i.e. post requests to upload the data and get requests to download the data,
update first locally, all the userIds from the local tasks. But first, get the userId from jwt then update the userId from sharedPreferences, then
get and use that userId to udpate the userIds from all local tasks. Like get all tasks, then map each, then change each userId to the updated userId,
that was got from the remote/jwt/updated sharedPreferences.



TODO
F20
As a user, if I am logged in, I have the option to publicize my tasks, where other users have the option to comment on it.
- default behaviour is tasks are private

TODO
F21
As a user, I want to see my profile, which has the statistics of my tasks and other related data.
- stats like percentage of tasks done daily, average. like 40% tasks done in day 1, 60% tasks done in day 2, then 50% average.

TODO
F22
As a developer, I want to use ISO string for createdAt date formats for Task.
- current implementation is Long format. apply ISO string yyyy-MM-dd'T'HH:mm:ss.SSS'Z', similar to Javascript's toISOString()
-

TODO
F23
As a developer, I want to include versionKey field to match the backend fields and to be used to check what data is the latest one.
- for example, locally, versionKey is 1 since it was created locally. it is then synced with the remote, thus saved to remote database.
- now if we edit the Task locally, the versionKey locally is 2, but for remote it is still 1. So we follow the local versionKey, then override the remote data.
- second scenario, I have 2 devices. I created a new task, with versionKey one for device A, and I am online. Now I logged in to device B, and get
that Task and save it to my device. With versionKey of 1. Now I edited the task, and the versionKey increased to 2. Now I am online so my changes
will be reflected remotely, so the remote data has now that task of versionKey of 2. Now back to device A, since the correct and latest data
is from remote data, the local data (of device A) will be overridden.

TODO
F24
- third scenario, similar to scenario 2, but now, after the device B has edited the task, and saved it remotely with versionKey of 2, what if the user from
device A has saved the task locally, and decided to edit the task. The task will have now a versionKey of 3. What if he is online, so the task will be stored
to remote database. What if the device from device B also edited the task locally, so both tasks now will have the versionKey of 3. What to do?

TODO
F25
As a developer, I want to optimize the first load of the app
- use R8, profiling etc.

TODO
F26
Change type of createdAt (for Task) to String
- use toISOString.

TODO
B3
If i.press ang delete, nag stay muna siya. nauna lumipat to todolist screen bago mawala.yung delete.
programmatically hide the delete before moving to todolist.screen.

TODO B3 Crashed