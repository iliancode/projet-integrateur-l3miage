import { initializeApp } from 'firebase/app';
import { getFirestore } from 'firebase/firestore';

// Your Firebase configuration
const firebaseConfig = {
  projectId: 'projet-integrateur-d6e79',
  appId: '1:241941493244:web:c2928eb58731dcfd826e79',
  storageBucket: 'projet-integrateur-d6e79.appspot.com',
  apiKey: 'AIzaSyDtfnXPKQNq2gpd8ahcbhCl72drpA1kHK0',
  authDomain: 'projet-integrateur-d6e79.firebaseapp.com',
  messagingSenderId: '241941493244',
};

// Initialize Firebase app
const app = initializeApp(firebaseConfig);

// Create Firestore database instance
export const db = getFirestore(app);

// Export Firestore database instance as named export
