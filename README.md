# small-distance-courier-service
## Small Distance Courier Service

## Approach
- The SOLID design principles
- Test Driven Development
- Clean Code

## Language
- Kotlin

# Acceptance Criteria
Part 1 : Estimate the total delivery cost of each package with an offer code (If applicable).
- [x] Only one offer code can be applied for any given package.
- [x] Package should meet the required mentioned offer criteria.
- OFR001 10% Discount, Distance < 200 Km, Weight 70-200 KG.
- OFR002 7% Discount, Distance 50-150 Km, Weight 100-250 KG.
- OFR003 5% Discount, Distance 50-250 Km, Weight 10-150 KG.
- [x] If offer code is not valid/found, discounted amount will be equal to 0.
- [x] Code should be extensible & scalable for more offer codes.
- [x] Delivery Cost = Base Delivery Cost + (Package Total Weight * 10) + (Distance to Destination * 5).
- [x] Discount = (Offer / 100) * Delivery Cost.
- [x] Total Cost = Delivery Cost - Discount.
- [x] All input numbers will be whole numbers (Base Delivery Cost, Number of Packages, Package Weight in Kg, Distance in Km).

Part 2: Calculate the estimated delivery time for every package by maximizing no. of packages in every shipment.
- [ ] Each Vehicle has a limit (L) on maximum weight (kg) that it can carry.
- [ ] All Vehicles travel at the same speed (S km/hr) and in the same route.
- [ ] Delivery should meet the required mentioned delivery criteria.
- Shipment should contain max packages vehicle can carry in a trip. (Optimum Combination nearing vehicle max weight limit but not exceeding it).
- We should prefer heavier packages when there are multiple shipments with the same no. of packages.
- If the weights are also the same, preference should be given to the shipment which can be delivered first. (Shortest Distance)
- [ ] Always travelling at max speed.
- [ ] Post package delivery, vehicle will return back to the source station with the same speed and will be available for another shipment.
- [ ] All input numbers will be whole numbers (Number of Vehicles, Max Speed, Max Weight).
- [ ] Round Of estimated delivery time up to 2 digits. (for example 3.456 becomes 3.45) (Truncate).

### Additional Acceptance Criteria
- [x] Expansion : If offer code is not valid/found, discounted amount will be equal to 0 (Application: Invalid / Can not be found). OR Discount will not be provided if no offer code is applied (User : Did not provide).
- [ ] Expansion : Round Of estimated delivery time up to 2 digits. (for example 3.456 becomes 3.45) (Truncate). Truncate all digital Output to be consistent with the requirement.

# Use Case
Actor : User
- [x] Input Base Delivery Cost, Number of Packages
- [x] Input Package ID, Package Weight in Kg, Distance in Km, Offer Code (If applicable)
- [ ] Input No. of Vehicles, Max speed, Max (L) Weight 

Actor : Application
- [x] Output directives
- [x] Validate Input Base Delivery Cost, Number of Packages
- [x] Validate Input Package ID, Package Weight in Kg, Distance in Km, Offer Code (If applicable)
- [ ] Validate Input No. of Vehicles, Max speed, Max (L) Weight
- [x] Output Error (If applicable)
- [x] Validate Offer Criteria
- [x] Estimate the total delivery cost of each package with an offer code (If applicable).
- [ ] Validate Deliver Criteria
- [ ] Calculate the estimated delivery time for every package by maximizing no. of packages in every shipment.