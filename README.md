# Minimalist Tracker  
[![License: MIT](https://img.shields.io/badge/License-MIT-red.svg)](https://opensource.org/licenses/MIT)

This is a minimalist calorie tracker app with *natural language* API from CalorieNinjas.
Just search your food, and the API will automatically understand your query and add it to the tracker.
No fuss.

## Screenshots
<p float="left">
  <img src="https://github.com/hahmraro/minimalist_tracker/blob/master/screenshots/Screenshot1.jpeg" width="32%" />
  <img src="https://github.com/hahmraro/minimalist_tracker/blob/master/screenshots/Screenshot2.jpeg" width="32%" />
  <img src="https://github.com/hahmraro/minimalist_tracker/blob/master/screenshots/Screenshot3.jpeg" width="32%" />
</p>
<p float="left">
  <img src="https://github.com/hahmraro/minimalist_tracker/blob/master/screenshots/Screenshot4.jpeg" width="32%" />
  <img src="https://github.com/hahmraro/minimalist_tracker/blob/master/screenshots/Screenshot5.jpeg" width="32%" />
  <img src="https://github.com/hahmraro/minimalist_tracker/blob/master/screenshots/Screenshot6.jpeg" width="32%" />
</p>

## Installation
Clone this repository and import into **Android Studio**
```bash
git clone git@github.com:hahmraro/minimalist_tracker.git
```

## Configuration
### API key:
Go to the [CalorieNinjas API site](https://calorieninjas.com/api) and get your free API key.
Create `local.properties` in the root of the project and add this line:
```gradle
apiKey="*YOUR API KEY HERE*"
```
Replacing where it says "YOUR API KEY HERE" with your own key that you got from the API site.
Now just build the project and everything should work fine.

## Maintainers
This project is maintained by:
* [João Pegoraro](http://github.com/hahmraro)

## Contributing

1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -m 'Add some feature')
4. Run the [linter](https://ktlint.github.io/) (The [AndroidStudio plugin](https://plugins.jetbrains.com/plugin/15057-ktlint-unofficial-) works just fine too).
5. Push your branch (git push origin my-new-feature)
6. Create a new Pull Request
