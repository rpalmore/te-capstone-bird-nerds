import axios from 'axios';

export default {

    getNotes(birdID) {
        return axios.get('/notes/' + birdID);
    },

    deleteNote(noteID) {
        return axios.delete('/deleteNote/' + noteID);
    },

    editNote(noteID) {
        return axios.put('/editNote/' + noteID);
    },

    addNote(birdNote) {
        return axios.post('/addNote', birdNote);
    },

    getNote() {
        return axios.get('/note');
    }
}




