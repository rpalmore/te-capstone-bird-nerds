import axios from 'axios';

export default {

    getBirdsInList(listId) {
        return axios.get(`/lists/${listId}/birds`);
    },

    getBird(birdId) {
        return axios.get(`/bird/${birdId}`);
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
        return axios.get(`/birds/${zipcode}`);
    },

    getRandomBird() {
        return axios.get('/randomBird');
    }

    


}