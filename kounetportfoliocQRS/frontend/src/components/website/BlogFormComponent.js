import React, { useState } from 'react';
import { Formik, Form } from 'formik';
import * as Yup from 'yup';
import {
    TextField,
    Button,
    Typography,
    Box,
    Stack
} from '@mui/material';
import axios from 'axios';
import { API_URL_ADD_ARTICLE, API_URL_UPDATE_ARTICLE } from '../../api/blog/command';

const ArticleSchema = Yup.object().shape({
    title: Yup.string().required('Title is required'),
    content: Yup.string().required('Content is required'),
    createdAt: Yup.string().required('Date is required'),
    authorId: Yup.string().required('Author ID is required'),
    domainId: Yup.string().required('Domain ID is required'),
    tagIds: Yup.string(),
    commentIds: Yup.string(),
});

const BlogFormComponent = ({ articleId, initialData }) => {
    const [previewUrl, setPreviewUrl] = useState(initialData?.urlMedia || '');
    const [selectedFile, setSelectedFile] = useState(null);

    const defaultInitialValues = {
        title: '',
        content: '',
        createdAt: new Date().toISOString().split('T')[0],
        authorId: '',
        domainId: '',
        tagIds: '',
        commentIds: ''
    };

    const handleFileChange = (e) => {
        const file = e.target.files[0];
        if (file) {
            setSelectedFile(file);
            setPreviewUrl(URL.createObjectURL(file));
        }
    };

    return (
        <Box sx={{ maxWidth: 600, mx: 'auto', mt: 4 }}>
            <Typography variant="h5" gutterBottom>
                {articleId ? 'Update' : 'Create'} Article
            </Typography>

            <Formik
                initialValues={initialData || defaultInitialValues}
                validationSchema={ArticleSchema}
                onSubmit={async (values, { setSubmitting, resetForm }) => {
                    const formData = new FormData();

                    formData.append('title', values.title);
                    formData.append('content', values.content);
                    formData.append('createdAt', values.createdAt);
                    formData.append('authorId', values.authorId);
                    formData.append('domainId', values.domainId);
                    formData.append('tagIds', values.tagIds);
                    formData.append('commentIds', values.commentIds);

                    if (selectedFile) {
                        formData.append('media', selectedFile); // le champ doit correspondre au nom dans le backend
                    }

                    try {
                        if (articleId) {
                            await axios.put(API_URL_UPDATE_ARTICLE(articleId), formData, {
                                headers: { 'Content-Type': 'multipart/form-data' }
                            });
                            alert('Article updated successfully!');
                        } else {
                            await axios.post(API_URL_ADD_ARTICLE, formData, {
                                headers: { 'Content-Type': 'multipart/form-data' }
                            });
                            alert('Article created successfully!');
                            resetForm();
                            setPreviewUrl('');
                            setSelectedFile(null);
                        }
                    } catch (error) {
                        console.error('Error saving article:', error);
                        alert('Error saving article.');
                    } finally {
                        setSubmitting(false);
                    }
                }}
            >
                {({ values, errors, touched, handleChange, isSubmitting }) => (
                    <Form encType="multipart/form-data">
                        <Stack spacing={2}>
                            <TextField
                                label="Title"
                                name="title"
                                value={values.title}
                                onChange={handleChange}
                                error={touched.title && Boolean(errors.title)}
                                helperText={touched.title && errors.title}
                            />

                            <TextField
                                label="Content"
                                name="content"
                                multiline
                                rows={4}
                                value={values.content}
                                onChange={handleChange}
                                error={touched.content && Boolean(errors.content)}
                                helperText={touched.content && errors.content}
                            />

                            <TextField
                                label="Created At"
                                name="createdAt"
                                type="date"
                                value={values.createdAt}
                                onChange={handleChange}
                                error={touched.createdAt && Boolean(errors.createdAt)}
                                helperText={touched.createdAt && errors.createdAt}
                                InputLabelProps={{ shrink: true }}
                            />

                            <TextField
                                label="Author ID"
                                name="authorId"
                                value={values.authorId}
                                onChange={handleChange}
                                error={touched.authorId && Boolean(errors.authorId)}
                                helperText={touched.authorId && errors.authorId}
                            />

                            <TextField
                                label="Domain ID"
                                name="domainId"
                                value={values.domainId}
                                onChange={handleChange}
                                error={touched.domainId && Boolean(errors.domainId)}
                                helperText={touched.domainId && errors.domainId}
                            />

                            <TextField
                                label="Tag IDs (comma separated)"
                                name="tagIds"
                                value={values.tagIds}
                                onChange={handleChange}
                            />

                            <TextField
                                label="Comment IDs (comma separated)"
                                name="commentIds"
                                value={values.commentIds}
                                onChange={handleChange}
                            />

                            <Button
                                variant="outlined"
                                component="label"
                            >
                                Upload Image
                                <input
                                    type="file"
                                    hidden
                                    accept="image/*"
                                    onChange={handleFileChange}
                                />
                            </Button>

                            {previewUrl && (
                                <Box>
                                    <Typography variant="subtitle2">Image Preview:</Typography>
                                    <img
                                        src={previewUrl}
                                        alt="Preview"
                                        style={{ width: '100%', maxHeight: 300, objectFit: 'cover' }}
                                    />
                                </Box>
                            )}

                            <Button variant="contained" type="submit" disabled={isSubmitting}>
                                {articleId ? 'Update' : 'Create'} Article
                            </Button>
                        </Stack>
                    </Form>
                )}
            </Formik>
        </Box>
    );
};

export default BlogFormComponent;
