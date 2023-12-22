package services;

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

    public static Collection<Long> getAllActivatedUsers(){
        return usersRepository.keySet();
    }

}
