package com.accountant;

//import departmen.Account;
import java.util.Scanner;
import java.util.HashMap;
import java.lang.Character;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MainAccount{
	private static Scanner scan = new Scanner(System.in);
  private static HashMap<Integer,Account> accountMap = new HashMap<Integer,Account>();
	public static void main(String[]args){
		
        //Local Variable
        int action = 0;
        do{
           System.out.println("=== THE BANK ===");
           System.out.println("Choose action...");
           System.out.println("[1] ADD: Add new account");
           System.out.println("[2] READ: Read account");
           System.out.println("[3] UPDATE: Update account");
           System.out.println("[4] DELETE: Delete account");
           System.out.println("[5] PRINT: Print account");
           System.out.println("[6] EXIT");
           action = scan.nextInt();

           printLine();
           runAction(action);
           printLine();


       }while(action != 6);
	}
       //Print break Line
	public static void printLine(){
		for (int i=0;i<50 ; i++) {
			System.out.print("=");
		}
		System.out.print("\n");
	}

          public static void runAction(int action){
          	switch (action){
          		case 1:
          		     addAccount();
          		     break;
          		case 2:
          		      readAccount();
          		      break;
          		case 3:
          	        updateAccount();
          		      break;
          		case 4:
          		      deleteAccount();
          		      break;
          		case 5:
          		      printAccount();
          		      break;
          		case 6:
          		System.out.println("Thank you from the World Bank!");
                    break;
          		default:
          		System.out.println("Wrong action!");
          	}
          }

            
      //Add new account
            public static void addAccount(){
              int id = 0;
              String name = " ";
              String nrc = " ";
              boolean isKey = false;

              System.out.println("=== ADD NEW ACCOUNT ===");

              System.out.print("Enter new account id: ");
                id = scan.nextInt(); //buffer \n
                  for(Integer key: accountMap.keySet()){
                if(id == key){
                  printLine();
                  System.out.println("This account is already existed!");
                  isKey = true;
                  break;
                }else
                  isKey = false;
                }
                  // if(id == key){

                if (isKey == false) {
                  
                
                scan.nextLine();

                System.out.print("Enter new account name: ");
                name = scan.nextLine();

                System.out.print("Enter new nrc no: ");
                nrc = scan.nextLine();
                
                //Create new account when object's instantiate
                

                //Add account to HashMap
                printLine();
                  System.out.println("Successfully added Account ID"+" "+id+"!");
                  Account account = new Account(id, name, nrc);
                  accountMap.put(id, account);
                } // System.out.println(accountMap);
                }
              
              
              
              //Read account
              public static void readAccount(){
                char action = '\0';
                if (accountMap.size()==0) {
                   System.out.println("No account!");
                }else{
                   System.out.println("=== READ ACCOUNT ===");
                   System.out.println("Choose actions...");

                   System.out.println("[A] ALL: Read all accounts");
                   System.out.println("[O] ONE: Select an account you want to read");
                   action = scan.next().charAt(0);
                   action = Character.toUpperCase(action);

                   switch(action) {
                    case 'A' :
                         for (Account account: accountMap.values()) {
                              printLine();
                              System.out.println(account);
                         }
                         break;
                    case  'O' :
                          System.out.print("Enter an account id you want to read: ");
                          int id = scan.nextInt();
                          boolean isID = false;
                          for(Integer key: accountMap.keySet()){
                           if (id == key) {
                              printLine();
                              System.out.println(accountMap.get(id));
                              isID = true;
                              break;
                           }
                           else{
                             isID = false;
                             }
                           }
                           if (isID == false) {
                              printLine();
                              System.out.println("This is invalid ID! Try another ID!");
                           }
                           break;
                     default:
                          printLine();
                          System.out.println("Wrong action! Try using given ones!!");
                   }
                }
              }
              //Delete account
              public static void deleteAccount(){
                char action = '\0';

                if (accountMap.size() == 0) {
                  System.out.println("No account!");
                }else{
                  System.out.println("=== DELETE ACCOUNT ===");
                  System.out.println("Choose action...");

                  System.out.println("[A] Delete all accounts");
                  System.out.println("[O] Select an account you want to delete");
                  action = scan.next().charAt(0); //a
                  action = Character.toUpperCase(action); //A

                  switch(action){
                    case 'A' :
                        accountMap.clear();
                        printLine();
                        System.out.println("Successfully deleted!");
                        break;
                    case 'O' :
                        boolean isAccount = false;

                        System.out.print("Enter remove account id: ");
                        int id = scan.nextInt();

                        for(Integer key: accountMap.keySet()){
                          if (key == id) {
                             //remove
                             accountMap.remove(id);
                             printLine();
                             System.out.println("Successfully removed account id"+" "+id+"!");
                             isAccount = true;
                             break;
                          }else{
                            isAccount = false;
                          }
                        }
                          if (isAccount == false) {
                             printLine();
                             System.out.println("No search account" +" "+id);
                          }
                        
                        break;
                    default :
                        System.out.println("Wrong action!");
                  }
                }
              }
                        //Update account
                        public static void updateAccount(){
                          String name = "";
                          String nrc = "";
                          char action = '\0';
                          boolean isAcc = false;
                          int id = 0;

                          if (accountMap.size()==0) {
                              System.out.println("No account!");
                    
                          } else if(accountMap.size()!=0){
                              System.out.println("=== UPDATE ACCOUNT ===");
                              System.out.print("Enter an account id you want to update: ");
                              id = scan.nextInt();
                            }
                              for(Integer key: accountMap.keySet()){
                                if (id == key) {
                              System.out.println("Choose action...");
                              System.out.println("[N] NAME: Update account name");
                              System.out.println("[C] NRC: Update nrc no");
                              action = Character.toUpperCase(scan.next().charAt(0));
                
                              

                              scan.nextLine();

                               switch(action){
                                case 'N':
                                    System.out.print("Enter update account name: ");
                                    name = scan.nextLine(); //Doe John

                                    accountMap.get(id).setAccountName(name);
                                    printLine();
                                    System.out.println("Successfully account name updated!");
                                    break;
                                 case 'C':
                                    System.out.print("Enter update nrc number: ");
                                    nrc = scan.nextLine(); 

                                    accountMap.get(id).setNrcNo(nrc);
                                    printLine();
                                    System.out.println("Successfully nrc number updated!");
                                    break;
                                  default:
                                     System.out.println("Wrong Input Character!");
                                   }
                                   break;

                                  }
                                   else {
                                    printLine();
                                    System.out.println("Wrong ID Number! Try another one!");

                                
                                                               
                                    }
                                   }
                                  // if(isAcc == false){
                                  //   printLine();
                                  //   System.out.println("Wrong ID Number! Try another one!");

                                  // }
                                
                              
                                }
                        //Print Account
                        public static void printAccount(){
                          if (accountMap.size() == 0) {
                               System.out.println("No account!");
                          }else{
                            try{
                               FileWriter fw = new FileWriter("account.txt");
                               Date date = new Date();
                               SimpleDateFormat currentDate = new SimpleDateFormat("dd(E)/MM(MMM)/yyyy");
                               SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm:ss a");

                               fw.write("=== THE WORLD BANK ===");
                               fw.write("\n\n"); 
                               fw.write("DATE: " + currentDate.format(date));
                               fw.write("\n\n");
                               
                               int no = 1;
                               for (Account account: accountMap.values()) {
                                  fw.write("NO: " + no + "," +account + "\n");
                                  no++;
                               }

                               fw.write("\n");
                               fw.write("TIME: " + currentTime.format(date));
                               fw.close();

                               System.out.println("Successfully printed!");
                               printLine();

                            }catch(IOException e){
                              System.out.println(e);
                            }
                                   try{
                                    File file = new File("account.txt");

                                    Scanner scan = new Scanner (file);
                                    while (scan.hasNextLine()){
                                      System.out.println(scan.nextLine());
                                    }
                                   }catch(FileNotFoundException e){
                                    System.out.println(e);
                                   }

                          }
                        }
            }
