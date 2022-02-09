import axios from 'axios';

export default {

    getNotes(birdID) {
        return axios.get(`/bird/${birdID}/notes`);
    },

    delete(noteID) {
        return axios.delete('/delete/' + noteID);
    },

    editNote(noteID) {
        return axios.put('/editNote/' + noteID);
    },

    addNote(birdNote) {
        return axios.post('/newNote', birdNote);
    },

    getNote() {
        return axios.get('/note');
    }
}




