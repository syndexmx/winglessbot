package services;

import botcontroller.Menu;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    static Map<Long, IndividualService> usersRepository = new HashMap<>();

    public static IndividualService getIndividualService(long id){
        if ( usersRepository.containsKey(id) ){
            return usersRepository.get(id);
        } else {
            IndividualService iService = new IndividualService();
            usersRepository.put(id,iService);
            return iService;
        }

    }

    public static void setAlias(long chatId, String alias){
        IndividualService iService = usersRepository.get(chatId);
        iService.setAlias(alias);
    }

    public static void setMenu(long chatId, Menu menu){
        IndividualService iService = usersRepository.get(chatId);
        iService.setMenu(menu);
    }

    public static Menu getMenu(long chatId){
        IndividualService iService = usersRepository.get(chatId);
        return iService.getMenu();
    }

    public static String getAlias(long chatId){
        IndividualService iService = usersRepository.get(chatId);
        return iService.getAlias();
    }

    public static Collection<Long> getAllActivatedUsers(){
        return usersRepository.keySet();
    }

}
