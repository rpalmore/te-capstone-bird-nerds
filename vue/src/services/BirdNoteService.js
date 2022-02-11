import axios from 'axios';

export default {

    getNotes(birdID) {
        return axios.get(`/bird/${birdID}/notes`);
    },

    deleteNote(noteID) {
        return axios.delete('/deleteNote/' + noteID);
    },

    editNote(note) {
        return axios.put('/editNote', note);
    },

    addNote(birdNote) {
        return axios.post('/newNote', birdNote);
    },

    getNote() {
        return axios.get('/note');
    }
}




