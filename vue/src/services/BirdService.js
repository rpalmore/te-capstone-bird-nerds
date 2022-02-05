import axios from 'axios';

export default {

    getBirdsInList(listId) {
        return axios.get(`/lists/${listId}/birds`);
    },

/*     getBird(birdId) {
        return axios.get();
    },

    deleteBird(birdId) {
        return axios.delete();
    },

    deleteAllBirdsInList(listId) {
        return axios.delete();
    },

    editBird(bird) {
        return axios.put();
    },

    createBird(bird) {
        return axios.post();
    },

    getBirdByZip(zipcode) {
        return axios.get();
    } */


}