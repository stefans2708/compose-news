# About

This is the sample application created by following [this tutorial](https://www.youtube.com/watch?v=9AekfR-EI-U&list=PLzZEuVaFb9Exi-pc8qtHBrrLg8bUn-TP6&index=1&pp=iAQB)
Used api - [documentation](https://newsapi.org/docs)


The code as almost completely the same, and there are a few things I don't really like:
1. Presentation
   - The page should be the state upon which the UI should be created
   - Remove usage of `composed` block for shimmer and maybe other custom modifiers since it is no longer recommended approach
   - Maybe add presentation/screen package where would home, bookmarks go ...
   - Why is `Box` required in the search bar composable?
   - Maybe object per bottom bar tab (NewsNavigator file)?
   - Make `navigateToTab` as an extension function to NavController

2. Data
   - The `Context` variable in the LocalUserManagerImpl which is in the Data layer
   - Add the separate classes for the room instead of using the ones from the domain layer
   - Add separate DI classes for remote and local data sources 