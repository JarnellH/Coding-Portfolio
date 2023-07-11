import React from 'react';
import { Text, StyleSheet, Button, View, Image } from 'react-native';
import ImageDetail from "../components/ImageDetail";



const ReaperTrials = ({navigation}) =>{
//This is the screen which will contain the cover photo and the Start button and the title 

return <View>
<Text style = {styles.text}>Reaper Trials</Text>
<Image source ={require('../../assets/ReaperLogo.jpeg')}/>

<Button
   title = {"Start"} 
   onPress={function(){navigation.navigate("Welcome")}}
/>


</View>

}

const styles = StyleSheet.create({
    text: {
       fontSize : 40,
       textAlign : 'center',
       textShadowColor : 'red',
       textShadowRadius : 5
    }

});

export default ReaperTrials;