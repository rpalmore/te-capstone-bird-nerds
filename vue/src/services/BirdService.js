import axios from 'axios';

export default {

    getBirdsInList(listId) {
        return axios.get(`/lists/${listId}/birds`);
    },

<<<<<<< HEAD
/*     getBird(birdId) {
        return axios.get();
=======
    getBird(birdId) {
        return axios.get(`/bird/${birdId}`);
>>>>>>> main
    },

    deleteBird(birdId) {
        return axios.delete(`/deleteBird/${birdId}`);
    },

    editBird(bird) {
        return axios.put('/updateBird', bird);
    },

    createBird(listId, bird) {
        return axios.post(`/lists/${listId}/addBird`, bird);
    },

    getBirdByZip(zipcode) {
<<<<<<< HEAD
        return axios.get();
    } */
=======
        return axios.get(`/birds/${zipcode}`);
    }
>>>>>>> main


}