import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:3000"
});

export default {

    getProfile() {
        return http.get('/profile');
    },

    updateProfile(profile) {
        return http.post('/createProfile', profile);
    },

    editProfile(profile) {
        return http.put('/editProfile', profile)
    },

    deleteProfile(username) {
        return http.delete('/deleteProfile/' + username);
    }


}