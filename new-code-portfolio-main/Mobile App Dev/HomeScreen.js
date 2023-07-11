import React from 'react';
import { Text, StyleSheet, Button, View, TouchableOpacity } from 'react-native';

const HomeScreen = ({navigation}) => {
   //These are all buttons which will navigate to a different screen upon press
  return <View>
   <Text style={styles.text}> Home Screen</Text>
  <Button 
    onPress ={function(){navigation.navigate("Components")}}
    title = {"Components Screen"}  
    />
    

    <Button
      onPress ={function(){navigation.navigate("Counter")}}
      title = {"Counter Screen"}
    />

 
    
    <Button
      onPress={function(){navigation.navigate("Color")}}
      title = {"Color Screen"}
    />

    <Button
      onPress={function(){navigation.navigate("Square")}} 
      title ={"Square Screen"}
    />
    
    <Button 
      onPress = {function(){navigation.navigate("Reaper")}}
      title = {"Reaper Trials"}
    />
   </View>
};

const styles = StyleSheet.create({
  text: {
    fontSize: 30,
  },
});

export default HomeScreen;
