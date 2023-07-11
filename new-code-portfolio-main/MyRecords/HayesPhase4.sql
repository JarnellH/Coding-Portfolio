
SELECT Address, StreetName
FROM House , Agent
WHERE Office LIKE 'New Orleans Office';


SELECT Address,StreetName
FROM House ,Seller ,Agent 
WHERE SellerName = A_Name;


SELECT A_Name
FROM Agent ,Buyer 
WHERE A_Id = BuyerAgentId
AND MinPrice > 80000 
AND MaxPrice < 225000;
               
                
 
SELECT A_Name , Office ,COUNT(*)
FROM Agent,Buyer,House
WHERE  A_Id = BuyerAgentId
AND HouseAgentId = A_Id
GROUP By A_Name ,Office ,BuyerAgentId;



                 
--5        
SELECT Address , StreetName ,COUNT(*)
FROM House ,Agent ,Buyer
WHERE HouseAgentId = A_Id
AND A_Id = BuyerAgentId
GROUP BY Address , StreetName,BuyerAgentId 
HAVING COUNT (*) >= 1;



--6
SELECT Address , StreetName ,COUNT(*)
FROM House ,Agent ,Buyer
WHERE HouseAgentId < A_Id
AND A_Id < BuyerAgentId
GROUP BY Address , StreetName 
HAVING COUNT (*) = 1;




--7
SELECT A_Name , AVG (Commission)
FROM Agent, Posting , House 
WHERE  A_Id = PostingAgentId
AND A_Id = HouseAgentId
GROUP BY A_Name , A_Id;


--8 avg price for houses in Louisiana
SELECT AVG (AskPrice)
FROM  Posting , House 
WHERE StreetName LIKE 'Louisiana' 
AND   Address = 504
Group By Address, StreetName;


--9
SELECT A_Name , CellNum , COUNT(*)
FROM Agent , PhoneNumber
WHERE P_AgentId = A_Id
GROUP BY A_Name ,CellNum , P_AgentId;


                                 
SELECT A_Name,COUNT(*) 
FROM Agent,Buyer 
WHERE A_Id = BuyerAgentId
Group By A_Name
HAVING COUNT(*) = 2;


SELECT A_Name ,COUNT(*)
FROM Agent , Posting , House
WHERE HouseAgentId = A_Id
GROUP BY A_Name
HAVING AVG (Commission) > 10000;


SELECT BuyerName
FROM Buyer , House, Agent,Posting
WHERE A_Id = PostingAgentId
AND A_Id = HouseAgentId
AND A_Id = BuyerAgentId
Group BY BuyerName,AskPrice
HAVING AskPrice = (Select MIN(AskPrice)
FROM Posting);



--13                    
SELECT A_Name , BuyerName
FROM Agent , Buyer , Posting ,House
WHERE AskPrice BETWEEN MinPrice AND MaxPrice
AND A_Id = PostingAgentId 
AND A_Id = HouseAgentId 
AND A_Id = BuyerAgentId   
Group By A_Name , BuyerName;


--14
SELECT SellerSSN , SellerName
FROM Seller
WHERE REGEXP_LIKE (SellerSSN , '[321]*');



--15
SELECT A_Name , Office
FROM Agent 
WHERE REGEXP_LIKE(Office ,'[A-z]{5}\s[A-z]{3,8}');


--16
SELECT Address , StreetName 
FROM House
WHERE REGEXP_LIKE(Address ,'[0-9]{1,5}');


                 
                 
                 
                 
                 
