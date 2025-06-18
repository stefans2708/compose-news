# About

This is the sample application created by following [this tutorial](https://www.youtube.com/watch?v=9AekfR-EI-U&list=PLzZEuVaFb9Exi-pc8qtHBrrLg8bUn-TP6&index=1&pp=iAQB)
Used api - [documentation](https://newsapi.org/docs)
Everything presented on this tutorial was implemented on the main branch. Branch `dev` contains the code that I have
implemented additionally.


The code as almost completely the same, and there are a few things I don't really like:
1. Presentation
   - Remove usage of `composed` block for shimmer and maybe other custom modifiers since it is no longer recommended approach
   - Maybe add presentation/screen package where would home, bookmarks go ...
   - Why is `Box` required in the search bar composable?
   - Maybe object per bottom bar tab (NewsNavigator file)?
   - Make `navigateToTab` as an extension function to NavController
   - Check if navigation has changed (Objects instead of string routes)

2. Data
   - The `Context` variable in the LocalUserManagerImpl which is in the Data layer
   - Add the separate classes for the room instead of using the ones from the domain layer
   - Add separate DI classes for remote and local data sources


The `dev`

1. Category screen

- [x] Implement a screen with custom pagination (not using the Paging library)

- [ ] Implement search using existing search bar, but now with observing flow
  - Do not use any composable api in view model
  - Check this example https://github.com/santansarah/city-api-client/blob/search-type-flow/app/src/main/java/com/example/cityapiclient/presentation/search/SearchViewModel.kt
    from this yt video: https://www.youtube.com/watch?v=KxcttMg-JVI

- [ ] Create a bottom dialog screen for configuring:
  - Categories (country and some other category if available on API)
  - Filters should be visible as pills after application and can be dismissed separately on "x"
  - Item UI
    1. standard full-width items
    2. items in columns (maybe even option to configure a number of columns i.e. 2 or 3)

- [ ] Additional - Handle empty states:
  1. No items to load
  2. No more items (last page)
  3. No internet etc.