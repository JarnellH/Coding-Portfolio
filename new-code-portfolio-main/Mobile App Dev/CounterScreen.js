import  React, {useState} from 'react';
import {View, Text, StyleSheet, Button } from 'react-native';


const CounterScreen = () => {
    //The name of the counter variable couldve been anything 

    //the same logic applies for the attribute screen just position the buttons and update the values 
    
    const [counter, setCounter] = useState(0);
    console.log(counter);

    return <View>
        <Button title = "Increase" onPress= {function(){
            setCounter(counter + 1);
            console.log(counter);
        }} />
        <Button title= "Decrease" onPress = {function(){
            setCounter(counter - 1);
            console.log(counter);
        }} />
        <Text>Current Count : {counter} </Text>


    </View>
}

const style = StyleSheet.create({});

export default CounterScreen;