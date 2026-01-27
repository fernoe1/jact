import mongoose from 'mongoose';
import bcrypt from 'bcrypt';

const Schema = mongoose.Schema;

const userSchema = new Schema({
    name: {
        type: String,
        required: true
    },
    email: {
        type: String,
        required: true,
        unique: true
    },
    password: {
        type: String,
        required: true
    },
    role: {
        type: String,
        enum: ['user', 'guide', 'lead-guide', 'admin'],
        default: 'user'
    }
});

userSchema.statics.login = async function (email, password) {
    if (!email || !password) {
        throw Error('All fields must be filled');
    }

    const user = await this.findOne({ email });

    if (!user) {
        throw Error('Incorrect email');
    }

    const match = await bcrypt.compare(password, user.password);

    if (!match) {
        throw Error('Incorrect password');
    }

    return user;
}

userSchema.statics.signup = async function (name, email, password) {
    const exists = await this.findOne({ email });

    if (exists) {
        throw Error('Email already in use');
    }

    const salt = await bcrypt.genSalt();
    const hash = await bcrypt.hash(password, salt);

    const user = await this.create({ name, email, password: hash });

    return user;
}

const User = mongoose.model('User', userSchema);

export default User;