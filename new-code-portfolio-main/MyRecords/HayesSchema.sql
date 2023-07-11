    
    DROP TABLE Posting;
    DROP TABLE PhoneNumber;
    DROP TABLE House;
    DROP TABLE Buyer;
    DROP TABLE Seller;
    DROP TABLE agent;
    
    
--Agent (Id , Name , Office)
CREATE TABLE agent (
    Agent_Id NUMBER (3),
    A_Name CHAR (15),
    Office CHAR (18),
    
    CONSTRAINT agentPk PRIMARY KEY (Agent_Id)
);



-- PhoneNumber(AgentId , CellNum)
CREATE TABLE PhoneNumber(
  P_AgentId NUMBER(3),
  CellNum CHAR (10),
  
  
  CONSTRAINT PhoneNumberPk PRIMARY KEY (P_AgentId , CellNum),
  CONSTRAINT PhoneNumberFk FOREIGN KEY (P_AgentId) REFERENCES agent (Agent_Id)

);


--Buyer(SSN, Name, Telephone,MinPrice,MaxPrice,AgentId)
CREATE TABLE Buyer(
    BuyerSSN NUMBER (3),
    BuyerName CHAR (15),
    Telephone CHAR (10),
    MinPrice NUMBER(6,2),
    MaxPrice NUMBER(6,2),
    BuyerAgentId NUMBER (3),
    
    CONSTRAINT BuyerPk PRIMARY KEY (BuyerSSN),
    CONSTRAINT BuyerFk FOREIGN KEY (BuyerAgentId) REFERENCES agent(Agent_Id)
    
);


--Seller (SSN,Name,Spouse,PhoneNumber)
CREATE TABLE Seller(
    SellerSSN NUMBER (4),
    SellerName CHAR (15),
    SpouseName CHAR (15),
    SellerPhoneNum CHAR (10),
    
    CONSTRAINT SellerPk  PRIMARY KEY (SellerSSN)
    
);


--House(Id , Address , StreetName , SquareFt, AgentId , SellerSSN)
CREATE TABLE House (
    HouseId NUMBER (3),
    Address NUMBER (4),
    StreetName CHAR (24),
    SquareFt NUMBER (5),
    HouseAgentId NUMBER (3),
    HouseSellerSSN NUMBER(4),
    
    CONSTRAINT HousePk PRIMARY KEY (HouseId),
    CONSTRAINT HouseFk FOREIGN KEY (HouseAgentId) REFERENCES agent(Agent_Id),
    CONSTRAINT HouseFk2 FOREIGN KEY (HouseSellerSSN) REFERENCES Seller(SellerSSN)

);


--Listing(AgentId, HouseId ,Commission , AskPrice)
CREATE TABLE Posting (
    PostingAgentId NUMBER (3),
    PostingHouseId NUMBER (3),
    Commission int CONSTRAINT BoundsIn CHECK (Commission <= 100),
    AskPrice int CONSTRAINT PriceInRange CHECK (AskPrice <= 100),

    CONSTRAINT PostingPk PRIMARY KEY (Commission, AskPrice),
    CONSTRAINT PostingFk FOREIGN KEY (PostingHouseId) REFERENCES House(HouseId),
    CONSTRAINT PostingFk2 FOREIGN KEY (PostingAgentId) REFERENCES agent(Agent_Id)

);

