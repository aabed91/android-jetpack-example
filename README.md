# android-jetpack-example

Android JetPack example

This project is a simple example project using Android JetPack.

This app lists some posts from https://jsonplaceholder.typicode.com on RecyclerView and provide:
- See post details
- Delete post
- Edit post
- Add new post

The app supports online/offline to list data.

Note: delete/edit/add are faked (API is called as real) but the action not performed on the real data source because the API is public and used just for examples.

What we used in this project:
- Repository Pattern
- Retrofit2
- Room database
- LiveData and ViewModel
- DataBinding
- DataSource
- Navigation component and SafeArgs
- Glide
