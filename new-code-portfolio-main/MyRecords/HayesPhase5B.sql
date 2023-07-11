Alter Table Agent 
Add HousesRepresented Number default 0;


Create or Replace Trigger AddingHouse
After Insert or Update on House
For Each Row 
Declare 
tracker number;

Begin

Select Count(*) into tracker
from Agent , House 
Where Agent_Id = HouseAgentId
group by Agent_Id ;

 if (tracker >= 3) then
    RAISE_APPLICATION_ERROR(-20005,'Agent is Already completely booked'); 
    
else 

 Update Agent 
 Set HousesRepresented =  tracker + 1 
 where Agent_Id = :new.HouseAgentId;

end if; 
    
End;
/




 
 
 

Create Or Replace Trigger DeleteHouse
AFTER Delete on House 
for each row 
Declare 
counter number ;
Begin


Select Count(*) INTO counter
From Agent , House 
Where HouseAgentId = Agent_Id
Group by Agent_Id;

if (counter = 0 ) then 
    RAISE_APPLICATION_ERROR(-20001,'Agent does not represent any House yet.');    
else
    
    Update Agent 
    Set HousesRepresented = counter - 1
    where Agent_Id = :new.HouseAgentId;
     
    end if ; 
End;
/






Create or Replace Trigger UpdatedAgent 
After Update of HouseAgentId on House
For each row 
Declare 

agenthouse integer;

Begin 



Select Count (*) into agenthouse
from Agent ,House
Where HouseAgentId = Agent_Id
group by Agent_Id;

if (agenthouse = 3) then 
  RAISE_APPLICATION_ERROR(-20001,'Agent is Already completely booked');   

else 

    Update Agent 
    Set HousesRepresented =  agenthouse - 1
    where Agent_Id = :old.HouseAgentId;
    
    Update Agent 
    set HousesRepresented = agenthouse + 1 
    where Agent_Id = :new.HouseAgentId;
    
    
    end if;

end;
/





