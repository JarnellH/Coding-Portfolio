Select * FROM Posting;
Select * FROM House;

DECLARE 

average number; 
mini number := 0;
maxi number := 0;
priceperft number;

price Posting.AskPrice%type;
temp price%type;
temp2 integer := 0;
helper2 integer := 0;
sqft House.SquareFt%type;

streetnumber House.Address%type;
address House.StreetName%type;
rating char(5);




Cursor mainprice is
    Select AskPrice, SquareFt from Posting , House where PostingHouseId = HouseId;
Cursor critics is
    Select Address,StreetName from House,Posting where PostingHouseId = HouseId;

begin 

open mainprice;
open critics;

loop
    Fetch mainprice into price , sqft;
    
    fetch critics into streetnumber , address;
   
    
    price := price * 1000;
    temp := price /sqft;
    
     if maxi < priceperft then
        maxi := priceperft;
        mini := maxi;
    end if; 
        
     if priceperft < mini then
        mini := priceperft;
        end if;
        
       if temp < 13.2 then
        rating := 'Below';
        end if;
        
       if temp > 13.2 then
        rating := 'Above';
        end if;
           
        
    Exit when mainprice%NOTFOUND;
    exit when critics%NOTFOUND;
    
    
    DBMS_OUTPUT.PUT_LINE('Asking Price of: '|| price ||' with '||sqft||' sqft has a price per sqft of '|| temp);
    DBMS_OUTPUT.PUT_LINE( TO_CHAR(streetnumber)||' '||RTRIM(address)||' charges '||temp||' per sqaure feet and is '||rating||' average price'); 
     DBMS_OUTPUT.PUT_LINE('');
    
    
    priceperft := temp; 
    helper2 := helper2 + temp;      
    
       
    END loop;
    
    average := helper2 / 5;
   
    DBMS_OUTPUT.PUT_LINE('Average price of all houses per sqaure feet: '|| average);
    DBMS_OUTPUT.PUT_LINE('Minimum price of all houses per square feet: '|| mini);
    DBMS_OUTPUT.PUT_LINE('Maximum price of all houses per square feet: '|| maxi);
        
    Close mainprice;
    close critics;
   
END ;
