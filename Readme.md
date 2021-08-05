#### **CFAgent**

A CFAgent egy alkuszcég nyilvántartásának alapjait hivatott lefektetni.\
Az alap, hogy az ügynökök, parnerek és biztosítások áttekinthetőek legyenek, de fontos szempont volt a bővíthetőség, hogy minimális változtatással az igényekhez igazítva lehessen továbbfejleszteni a nyilvántartó programot.

Indulásként négy entitás került létrehozásra.\
A program webböngészőben fut, az /api/cfagent ponton keresztül lehet vele kommunikálni. 

**Ügynök - agent entitás**\
Azonosító - id\
Név - name\
MNB azonosító - mnbNumber\
Aktív státusz - active\
Partnerlista - List(Partner) partners\
_egy-több kapcsolatban áll a Partner entitással_\
Az entitáson elvégezhető műveletek:\
Ügynök létrehozása POST /api/cfagent/agent\
Ügynök(ök) listázása névminta alapján GET /api/cfgent/agent\
Ügynök módosítása PUT /api/cfagent/agent/{id}\
Partner létrehozása ügynökhöz POST /api/cfagent/agent/{id}

**Partner - partner entitás**\
Azonosító - id\
Név - name\
Céges partner - company\
Aktív státusz - active\
Ügynök - agent\
_több-egy kapcsolatban áll az Agent entitással_\
Címlista - List(Address) addresses
_egy-több kapcsolatban áll az Address entitással_\
Biztosítás-lista - List(Insurance) insurances\
_egy-több kapcsolatban áll az Insurance entitással_\
Az entitáson elvégezhető műveletek:\
Partner létrehozása POST /api/cfagent/partner\
Partner(ek) listázása névminta alapján GET /api/partner/agent\
Partner módosítása PUT /api/cfagent/partner/{id}\
Partner törlése, a hozzátartotó biztosításokkal és címekkel DELETE /api/cfagent/partner/{id}\
Cím létrehozása partnerhez POST /api/cfagent/partner/address/{id}\
Biztosítás létrehozása partnerhez POST /api/cfagent/partner/insurance/{id}

**Insurance - biztosítás**\
Azonosító - id\
Név - name\
Biztosító - company\
Típus - type\
Biztosítás - insurance\
Aktív státusz - active\
Partner - partner\
_több-egy kapcsolatban áll az Partner entitással_\
Az entitáson elvégezhető műveletek:\
Biztosítás létrehozása POST /api/cfagent/insurance\
Biztosítás(ok) listázása névminta alapján GET /api/cfgent/insurance\
Biztosítás módosítása PUT /api/cfagent/insurance/{id}\

**Address - cím**\
Azonosító - id\
Irányítószám - postcode\
Város - city\
Utca - street\
Partner - partner\
_több-egy kapcsolatban áll az Partner entitással_\
Az entitáson elvégezhető műveletek:\
Cím létrehozása POST /api/cfagent/address\
Cím(ek) listázása névminta alapján GET /api/cfgent/address\
Cím módosítása PUT /api/cfagent/address/{id}\

Az alkalmazás megvalósítása Java Spring Boot keretrendszerben történt.\
Minden entitás külön Controller-t és Service-t kapott, ezen felül van egy plusz Controller és Service az általános lekérdezések tárolásához.





