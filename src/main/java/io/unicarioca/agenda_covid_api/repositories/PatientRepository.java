package io.unicarioca.agenda_covid_api.repositories;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;

import org.bson.Document;
import org.bson.conversions.Bson;

import io.unicarioca.agenda_covid_api.config.MongoConfig;
import io.unicarioca.agenda_covid_api.models.Patient;
import io.unicarioca.agenda_covid_api.pojo.ResponsePatient;

import static io.unicarioca.agenda_covid_api.utils.IteratorsUtils.toList;
import static com.mongodb.client.model.Filters.*;

import java.util.List;
import java.util.stream.Collectors;

public class PatientRepository {

    public final String DATABASE = System.getenv("MONGO_DB");
    public final String COLLECTION = "patient";

    public PatientRepository() {

    }

    public List<ResponsePatient> getPatients(Bson filter) throws Exception {

        try (MongoClient mongoClient = MongoConfig.getMongoClient()) {

            MongoDatabase db = mongoClient.getDatabase(DATABASE);
            MongoCollection<Patient> patientCollection = db.getCollection(COLLECTION, Patient.class);

            List<Patient> listModels;

            if (filter != null)
                listModels = toList(patientCollection.find(filter));
            else
                listModels = toList(patientCollection.find());

            return listModels.stream()
                .map(model -> createResponse(model))
                .collect(Collectors.toList());
        }
    }

    public ResponsePatient createResponse(Patient model) {
        ResponsePatient response = new ResponsePatient();
        
        response.setIdPatient(model.getIdPatient());
        response.setName(model.getName());
        response.setProfilePhoto(model.getProfilePhoto());
        response.setAge(model.getAge());
        response.setGender(model.getGender());
        response.setProfession(model.getProfession()); 


        return response;
    }

    private void setUniqueFields(MongoCollection<Patient> patientCollection) {
        IndexOptions indexUnique = new IndexOptions().unique(true);
        
        patientCollection.createIndex(new BasicDBObject("idPatient", 1), indexUnique);
        patientCollection.createIndex(new BasicDBObject("nome", 1), indexUnique);        
    }

    /**
     * Find Max ID and increment one
     */
    private Integer nextSequenceID() {
        try (MongoClient mongoClient = MongoConfig.getMongoClient()) {

            MongoDatabase db = mongoClient.getDatabase(DATABASE);
            MongoCollection<Patient> patientCollection = db.getCollection(COLLECTION, Patient.class);
            
            FindIterable<Patient> it = patientCollection.find();

            if (toList(it).size() == 0)
                return 0;

            return patientCollection.find()
                .sort(new Document("idPatient", -1))
                .first().getIdPatient() + 1;
        }
    }     
    
}
