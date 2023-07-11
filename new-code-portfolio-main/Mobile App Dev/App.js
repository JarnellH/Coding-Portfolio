import { createAppContainer } from "react-navigation";
import { createStackNavigator } from "react-navigation-stack";
import HomeScreen from "./src/screens/HomeScreen";
import CounterScreen from "./src/screens/CounterScreen";
import ColorScreen from "./src/screens/ColorScreen";
import SquareScreen from "./src/screens/SquareScreen";
import ReaperTrials from "./src/screens/ReaperTrials";
import WelcomeScreen from "./src/screens/WelcomeScreen";
import WizardScreen from "./src/screens/WizardScreen";


//When making new component show import the Component with its relative path 

const navigator = createStackNavigator(
  {
    Home: HomeScreen,
    //Character: CharacterScreen,
    //List : ListScreen,
    Counter : CounterScreen,
    Color : ColorScreen,
    Square : SquareScreen,
    Reaper : ReaperTrials,
    Welcome : WelcomeScreen,
    Wizard : WizardScreen
    
  },
  {
    initialRouteName: "Home",
    defaultNavigationOptions: {
      title: "App",
    },
  }
);

export default createAppContainer(navigator);
