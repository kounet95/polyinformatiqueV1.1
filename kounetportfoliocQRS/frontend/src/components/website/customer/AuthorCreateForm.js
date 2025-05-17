import React, { useState } from 'react';
import { createAuthor } from './.././../../api/author/command';

const AuthorCreateForm = () => {
  const [form, setForm] = useState({
    username: '',
    Lastname: '',
    email: '',
    name: '',
    phone: '',
    address: '',
    iteamIds: '',
    commentIds: '',
  });
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState('');
  const [error, setError] = useState('');

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setSuccess('');
    setError('');
    try {
      const payload = {
        ...form,
        iteamIds: form.iteamIds
          ? form.iteamIds.split(',').map((id) => id.trim())
          : [],
        commentIds: form.commentIds
          ? form.commentIds.split(',').map((id) => id.trim())
          : [],
      };
      await createAuthor(payload);
      setSuccess('Auteur créé avec succès !');
      setForm({
        username: '',
        Lastname: '',
        email: '',
        name: '',
        phone: '',
        address: '',
        iteamIds: '',
        commentIds: '',
      });
    } catch (err) {
      setError('Erreur lors de la création de l\'auteur.');
    }
    setLoading(false);
  };

  return (
    <div className="container mt-5" style={{ maxWidth: 600 }}>
      <div className="card shadow">
        <div className="card-header bg-primary text-white">
          <h3 className="mb-0">Créer un auteur</h3>
        </div>
        <form className="card-body" onSubmit={handleSubmit}>
          <div className="mb-3">
            <label className="form-label">Username :</label>
            <input
              type="text"
              name="username"
              className="form-control"
              value={form.username}
              onChange={handleChange}
              required
            />
          </div>
          <div className="mb-3">
            <label className="form-label">Lastname :</label>
            <input
              type="text"
              name="Lastname"
              className="form-control"
              value={form.Lastname}
              onChange={handleChange}
              required
            />
          </div>
          <div className="mb-3">
            <label className="form-label">Email :</label>
            <input
              type="email"
              name="email"
              className="form-control"
              value={form.email}
              onChange={handleChange}
              required
            />
          </div>
          <div className="mb-3">
            <label className="form-label">Name :</label>
            <input
              type="text"
              name="name"
              className="form-control"
              value={form.name}
              onChange={handleChange}
            />
          </div>
          <div className="mb-3">
            <label className="form-label">Phone :</label>
            <input
              type="text"
              name="phone"
              className="form-control"
              value={form.phone}
              onChange={handleChange}
            />
          </div>
          <div className="mb-3">
            <label className="form-label">Address :</label>
            <input
              type="text"
              name="address"
              className="form-control"
              value={form.address}
              onChange={handleChange}
            />
          </div>
          <div className="mb-3">
            <label className="form-label">Iteam IDs (séparés par des virgules) :</label>
            <input
              type="text"
              name="iteamIds"
              className="form-control"
              value={form.iteamIds}
              onChange={handleChange}
              placeholder="id1,id2,id3"
            />
          </div>
          <div className="mb-3">
            <label className="form-label">Comment IDs (séparés par des virgules) :</label>
            <input
              type="text"
              name="commentIds"
              className="form-control"
              value={form.commentIds}
              onChange={handleChange}
              placeholder="id1,id2"
            />
          </div>
          <button type="submit" className="btn btn-primary w-100" disabled={loading}>
            {loading ? 'Création...' : 'Créer'}
          </button>
          {success && <div className="alert alert-success mt-3">{success}</div>}
          {error && <div className="alert alert-danger mt-3">{error}</div>}
        </form>
      </div>
    </div>
  );
};

export default AuthorCreateForm;