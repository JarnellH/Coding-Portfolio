 import React from 'react';
 import {Text , View , StyleSheet, Image, Button, TouchableOpacity} from 'react-native';

 const ImageDetail = (props) =>{

    //console.log(props);

    return (
        <View>
            <Image source={props.path}/>
            <Text>{props.title}</Text>
            
        </View>
    );
 }

 const styles = StyleSheet.create({
    
 });

 export default ImageDetail;