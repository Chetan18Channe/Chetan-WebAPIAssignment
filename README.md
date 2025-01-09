# Customer Reward Program - Spring boot Application 

This Spring Boot application calculates reward points for customers based on their monthly transactions. The application follows a RESTful architecture and includes a fully functional backend service.

Project Overview

The Customer Rewards Program calculates reward points for customers based on their purchases. The rewards are calculated as:

1 point for every dollar spent between $50 and $100.
2 points for every dollar spent over $100.

Example:
A purchase of $120 will earn:
1 point for $50 (from $50 to $100) = 50 points
2 points for $20 (over $100) = 40 points
Total = 90 points

Spring Boot application that calculates reward points based on customer transactions. It includes the following:

1)Controller to handle RESTful requests.
2)Service to calculate reward points.
3)Model for transactions and customers.
4)Data set to demonstrate the solution.

Please refer CustomerReward.docx file and URL

URL:
http://localhost:8080/rewards/calculate

