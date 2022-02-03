import axios from 'axios';

export default {

    getList(listID) {
        return axios.get('/lists/' + listID);
    },

    getAllLists() {
        return axios.get('/lists');
    },

    createList(list) {
        return axios.post('/createList', list);
    },

    editList(list) {
        return axios.put('/editList', list);
    },

    deleteList(listID) {
        return axios.delete('/deleteList/' + listID);
    }
}