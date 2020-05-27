package org.vaadin.marcus.spring;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

@Service
public class GuestApplication extends Observable implements Observer {

    /** str[0]= "fail"/ "respond"
     *  str[1]= "fail"-> message / "respond" ->type -"IFA"
     *  str[2]= "respond"-> username
     *  str[3]= "respond"-> name
     *  str[4]= "respond"-> "message1"
     *  str[5]= "respond"-> "message2"
     * @param username
     * @param pass
     * @return
     */
    public String[] login (String username, String pass){
        String[] ans=new String[6];
        ans[0]="respond";
        ans[1]="IFA";
        ans[3]="Chen The Queen";
        return ans;
    }



    /** str[0]= "fail"/ "respond"
     *  str[1]= "fail"-> message
     * @param username
     * @param pass
     * @return
     */
    //TODO: after double pass check (get from gal)

    public String[] regiter (String name,String username, String pass){
        return null;
    }



    /** IFA!
     * str[0]= "fail"/ "respond"
     *  str[1]= "fail"-> message
     * @return
     */
    //TODO: after double pass check (get from gal)
    public String[] addTeam (String teamName,String ownerUserName){
        return null;
    }

    /** IFA!
     * str[0]= "fail"/ "respond"
     *  str[1]= "fail"-> message
     * @return
     */
    //TODO: after double pass check (get from gal)
    public String[] addOwner(String name,String username, String pass){
        return null;
        }


    /** IFA!
     * str[0]= "fail"/ "respond"
     *  str[1]= "fail"-> message / "respond"-> "policy1"
     *  str[2]= "respond"-> "policy2"
     * @return
     */
    public String[] getCalculatorPolicy(){
        return null;
    }

    /** IFA!
     * str[0]= "fail"/ "respond"
     *  str[1]= "fail"-> message
     * @return
     */
    public String[] setLegueCalculator(String legueName, int leagueYear,String policy ){
        return null;
    }


    /** IFA!
     * str[0]= "fail"/ "respond"
     *  str[1]= "fail"-> message / "respond"-> "policy1"
     *  str[2]= "respond"-> "policy2"
     * @return
     */
    public String[] getGamesScedualsPolicy(){
        return null;
    }

    /** IFA!
     * str[0]= "fail"/ "respond"
     *  str[1]= "fail"-> message / "respond"-> "policy1"
     *  str[2]= "respond"-> "policy2"
     * @return
     */
    public String[] setGamesSceduals(String legueName, int leagueYear,String policy ){
        return null;
    }


    /** mainRefree!
     * str[0]= "fail"/ "respond"
     *  str[1]= "fail"-> message
     * @return
     */
    public String[] addedEvent(String playerUserName, int gameID, String event, int minute, Date date){
        return null;
    }

    /** mainRefree!
     * str[0]= "fail"/ "respond"
     *  str[1]= "fail"-> message
     * @return
     */
    public String[] createReport(int gameID){
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg!=null){
            String [] s= (String[]) arg;
            //arg[0]= "notification"
            //arg[1]= "message1"
            //arg[2]= "message2"

            setChanged();
            notifyObservers(arg);
        }
    }


    public ArrayList<String> getMsg(String userName) {
        ArrayList<String> msgList=new ArrayList<>();
        for (int i=0;i<10;i++){
            msgList.add(userName+" "+i);
        }
        return msgList;
    }
}
