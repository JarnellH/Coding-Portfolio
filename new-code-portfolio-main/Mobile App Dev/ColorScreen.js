import React , {useState} from 'react';
import {Button , StyleSheet, View, FlatList} from 'react-native';


const ColorScreen = () =>{
    
    const [colors, setColors] = useState([]);


    ///<View style = {{backgroundColor : "rgb(0,255,0)"}}>
return <View> 
    <Button title="Add Color"
    onPress={() =>{
        setColors([...colors, randomRgb()]);
    }}
    />
    <FlatList
        data = {colors}
        renderItem = {({item}) => {
            return <View style = {{width: 100 , height: 100 ,backgroundColor: item }}></View>                

        }}
        keyExtractor = {(color) => {return color}}
    />
    
</View>

}

/*
this function returns random colors  
**/
const randomRgb = () => { 

    const red = Math.floor(Math.random() * 256);
    const green = Math.floor(Math.random() * 256);
    const blue = Math.floor(Math.random() * 256);

    //Back tick ` makes a template string  concatenation also works normally
    let colorString = `rgb( ${red}, ${green}, ${blue})`;
    return colorString;
}

const style = StyleSheet.create({});


export default ColorScreen;