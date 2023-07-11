import React, { useState } from 'react';
import { Text, StyleSheet, Button, View} from 'react-native';
import ColorCounter from '../components/ColorCounter';

const SquareScreen = () =>{
    const [red,setRed] = useState(0);

    const [green,setGreen] = useState(0);
    const [blue,setBlue] = useState(0);

    let count = 20;

    const setColor = (color, change) => {
        //color == red green blue 
        //change +20 ,-20
       
        

        if(color === 'red'){
            if(red + change < 0 || red + change > 255){
                return;
            }
            else {
                setRed(red + change);
            }
        }

        if(color === 'green'){
            if(green + change < 0 || green + change > 255){
                return;
            }
            else {
                setRed(green + change);
            }
        }

        if(color === 'blue'){
            if(blue + change < 0 || blue + change > 255){
                return;
            }
            else {
                setRed(blue + change);
            }
        }
    }


    return <View>
        <ColorCounter
            color = "Red"
            onIncrease = {() => { setColor('red', count);}}
            onDecrease = {() => { setColor('red', -1 * count);}}
           
            />
        <ColorCounter
             color = "Green"
             onIncrease = {() => { setColor('green', count);}}
             onDecrease = {() => { setColor('green', -1 * count);}}
            
            />
        <ColorCounter 
            color = "Blue"
            onIncrease = {() => { setColor('blue', count);}}
            onDecrease = {() => { setColor('blue', -1 * count);}}
            />
        <View style ={ {height: 150, width: 150, backgroundColor: `rgb(${red},${green},${blue})` }  }></View>
    </View>
}

const style = StyleSheet.create({})

export default SquareScreen;