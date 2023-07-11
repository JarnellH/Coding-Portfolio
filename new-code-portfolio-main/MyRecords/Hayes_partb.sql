

SELECT BuyerName
FROM Buyer 
WHERE MinPrice >= 150000;


SELECT * FROM Buyer 
WHERE BuyerAgentId = 1
ORDER BY MaxPrice ASC;


SELECT BuyerName
FROM Buyer 
WHERE Telephone LIKE '504-____';


SELECT (Address),(StreetName)
FROM House 
WHERE HouseId = (SELECT PostingHouseId
               FROM Posting
               WHERE AskPrice = 0);
    


SELECT (SquareFt)
FROM House
WHERE SquareFt BETWEEN 100000 AND 200000
AND HouseId = (SELECT(PostingHouseId) 
                FROM Posting 
                WHERE PostingHouseId = HouseId);
                
 

SELECT * 
FROM Buyer 
WHERE Telephone LIKE '312%' 
AND BuyerAgentId != 1;
 

SELECT MAX(SquareFt), MIN(SquareFt)
FROM House;


SELECT AVG (MaxPrice) 
FROM Buyer 
WHERE BuyerAgentId = 1; 







